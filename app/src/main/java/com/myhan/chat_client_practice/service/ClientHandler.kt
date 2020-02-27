package com.myhan.chat_client_practice.service

import android.util.Log
import com.myhan.chat_client_practice.model.ChatMessage

class ClientHandler {
    fun onJoin(chatMessage: ChatMessage): Unit {
        Log.d("debug", "join message: $chatMessage")
    }
    fun onSend(chatMessage: ChatMessage): Unit {
        Log.d("debug", "send message: $chatMessage")
    }
    fun onBroadcast(chatMessage: ChatMessage): Unit {
        Log.d("debug", "broadcast message: $chatMessage")
    }
    fun onLeave(chatMessage: ChatMessage): Unit {
        Log.d("debug", "leave message: $chatMessage")
    }
}