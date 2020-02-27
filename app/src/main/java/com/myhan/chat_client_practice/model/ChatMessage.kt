package com.myhan.chat_client_practice.model

data class ChatMessage(
    val type: MessageType,
    val roomId: String,
    val sender: String,
    val content: String
) {
    enum class MessageType {
        JOIN, SEND, LEAVE, BROADCAST
    }
}