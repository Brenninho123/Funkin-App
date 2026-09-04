package com.brenninho123.funkinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Chat(
    val id: String,
    val name: String,
    val lastMessage: String,
    val time: String
)

class Main : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val chats = listOf(
        Chat("1", "Boyfriend", "Beep boop!", "12:45"),
        Chat("2", "Girlfriend", "Ready for the battle?", "12:30"),
        Chat("3", "Pico", "Go Pico, yeah!", "11:15")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        Header()
        ChatList(chats = chats)
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1F1F1F))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Funkin' App",
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
fun ChatList(chats: List<Chat>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(chats) { chat ->
            ChatItem(chat = chat)
        }
    }
}

@Composable
fun ChatItem(chat: Chat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = chat.name,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = chat.lastMessage,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Text(
            text = chat.time,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}
