package com.myhan.chat_client_practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FragmentFriendViewModel(private val repo: Repository) : ViewModel() {
    private val me = repo.me()
    private val myLiveNickname = MutableLiveData<String>()
    private val myLiveComment = MutableLiveData<String>()

    fun users() = repo.users()

    fun myNickname(): LiveData<String> = myLiveNickname
    fun myComment(): LiveData<String> = myLiveComment

    private var myProfileDispose: Disposable

    init {
        myProfileDispose = me.observeOn(Schedulers.io())
            .subscribe {
                myLiveNickname.postValue(it.nickname)
                myLiveComment.postValue(it.comment)
            }
    }

    override fun onCleared() {
        myProfileDispose.dispose()
        super.onCleared()
    }
}
