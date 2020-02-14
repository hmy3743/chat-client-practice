package com.myhan.chat_client_practice

import androidx.lifecycle.ViewModel

class FragmentChattingViewModel(private val repo: Repository) : ViewModel() {
    fun chatRooms() = repo.chatRooms()
}
