package com.myhan.chat_client_practice.service.network

import com.myhan.chat_client_practice.model.ChatMessage
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface ChatClientService {
    @Receive
    fun observeWebsocketEvent(): Flowable<WebSocket.Event>

    @Receive
    fun observe(): Flowable<ChatMessage>

    @Send
    fun send(message: ChatMessage)
}