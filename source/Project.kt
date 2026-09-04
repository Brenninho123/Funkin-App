package com.brenninho123.funkinapp

import java.io.File

class Project(
    var name: String = "Funkin-App",
    var title: String = "Funkin' App",
    var version: String = "1.0.0",
    var company: String = "Brenninho123",
    var packageName: String = "com.brenninho123.funkinapp",
    var mainClass: String = "Main",
    var orientation: Orientation = Orientation.PORTRAIT,
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

    fun addLibrary(name: String, version: String? = null) {
        libraries.add(Library(name = name, version = version))
    }

    fun addDefine(name: String, value: String? = null) {
        defines.add(Define(name = name, value = value))
    }
}
