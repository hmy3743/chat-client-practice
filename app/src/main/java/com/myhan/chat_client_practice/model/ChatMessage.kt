package com.myhan.chat_client_practice.model

data class ChatMessage(
    val type: MessageType,
    val roomId: String,
    val sender: String,
    val message: String
) {
    enum class MessageType {
        JOIN, TALK
    }
}