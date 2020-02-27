package com.myhan.chat_client_practice.service

import com.myhan.chat_client_practice.model.ChatMessage
import io.reactivex.Flowable

interface Repository {
    fun observeJoin(): Flowable<ChatMessage>
    fun observeSend(): Flowable<ChatMessage>
    fun observeLeave(): Flowable<ChatMessage>
    fun observeBroadcast(): Flowable<ChatMessage>
    fun join(): Unit
}