import java.io.File

class Project(
    var name: String = "Funkin-App",
    var title: String = "Funkin' App",
    var version: String = "1.0.0",
    var company: String = "Brenninho123",
    var packageName: String = "com.brenninho123.funkinapp",
    var mainClass: String = "Main",
    var orientation: Orientation = Orientation.LANDSCAPE,
    var windowWidth: Int = 1280,
    var windowHeight: Int = 720,
    var fps: Int = 60,
    var sourcePaths: MutableList<String> = mutableListOf(),
    var libraries: MutableList<Library> = mutableListOf(),
    var defines: MutableList<Define> = mutableListOf()
) {

    enum class Orientation {
        PORTRAIT,
        LANDSCAPE,
        AUTO
    }

    data class Library(
        val name: String,
        val version: String? = null
    )

    data class Define(
        val name: String,
        val value: String? = null
    )

    init {
        detectSourceDirectories()
    }

    fun detectSourceDirectories(baseDir: File = File(".")) {
        sourcePaths.clear()
        val possiblePaths = listOf("source", "src", "sources", "src/main/kotlin", "src/main/java")
        
        for (path in possiblePaths) {
            val dir = File(baseDir, path)
            if (dir.exists() && dir.isDirectory) {
                sourcePaths.add(dir.path)
            }
        }

        if (sourcePaths.isEmpty()) {
            val defaultSource = File(baseDir, "source")
            if (!defaultSource.exists()) {
                defaultSource.mkdirs()
            }
            sourcePaths.add(defaultSource.path)
        }
    }

    fun trace() {
        println("=== PROJECT CONFIG ===")
        println("Name: $name")
        println("Title: $title")
        println("Version: $version")
        println("Company: $company")
        println("Package: $packageName")
        println("Main Class: $mainClass")
        println("Orientation: $orientation")
        println("Window: ${windowWidth}x${windowHeight} @ $fps FPS")

        println("Source Paths (${sourcePaths.size}):")
        sourcePaths.forEach { path ->
            val dir = File(path)
            val filesCount = dir.walkTopDown().filter { it.isFile }.count()
            println("  - Path: $path (Files found: $filesCount)")
        }

        println("Libraries (${libraries.size}):")
        libraries.forEach { lib ->
            println("  - Name: ${lib.name} | Version: ${lib.version ?: "latest"}")
        }

        println("Defines (${defines.size}):")
        defines.forEach { def ->
            println("  - ${def.name}${if (def.value != null) " = ${def.value}" else ""}")
        }
        println("======================")
    }

    fun addLibrary(name: String, version: String? = null) {
        libraries.add(Library(name = name, version = version))
    }

    fun addDefine(name: String, value: String? = null) {
        defines.add(Define(name = name, value = value))
    }
}

fun main() {
    val project = Project().apply {
        addLibrary("flixel", "5.5.0")
        addLibrary("flixel-addons")
        addLibrary("flixel-ui")

        addDefine("FLX_NO_GAMEPAD")
        addDefine("DESKTOP_BUILD", "true")
    }

    project.trace()
}
