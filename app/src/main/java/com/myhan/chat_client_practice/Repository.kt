package com.myhan.chat_client_practice

import androidx.lifecycle.LiveData
import com.myhan.chat_client_practice.model.ChatMessage
import com.myhan.chat_client_practice.model.ChatRoom
import com.myhan.chat_client_practice.model.User
import io.reactivex.Flowable

interface Repository {
    fun chatRooms(): LiveData<List<ChatRoom>>
    fun users(): LiveData<List<User>>
    fun me(): Flowable<User>
    fun chatRoom(roomId: String): Flowable<ChatRoom>
    fun messages(roomId: String): LiveData<List<ChatMessage>>
}