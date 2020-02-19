package com.myhan.chat_client_practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myhan.chat_client_practice.model.ChatRoom
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ActivityChattingViewModel(private val repo: Repository): ViewModel() {
    lateinit var roomId: String
    private val liveChatRoomTitle = MutableLiveData<String>()
    private val liveChatRoom = MutableLiveData<ChatRoom>()
    private lateinit var disposableChatRoom: Disposable

    fun bindRoom(roomId: String){
        this.roomId = roomId
        disposableChatRoom = repo.chatRoom(roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                liveChatRoom.postValue(it)
                liveChatRoomTitle.postValue(it.name)
            }
    }

    fun chatRoom() = liveChatRoom as LiveData<ChatRoom>

    fun chatRoomTitle() = liveChatRoomTitle as LiveData<String>

    fun messages() = repo.messages(roomId)

    fun me() = LiveDataReactiveStreams.fromPublisher(repo.me())

    override fun onCleared() {
        disposableChatRoom.dispose()
        super.onCleared()
    }
}