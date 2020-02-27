package com.myhan.chat_client_practice.service

import android.util.Log
import com.myhan.chat_client_practice.model.ChatMessage
import com.myhan.chat_client_practice.service.network.ChatClientService
import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject

class RepositoryImpl(private val chatClientService: ChatClientService): Repository {
    private val flowableWebsocketEvent = chatClientService.observeWebsocketEvent()
    private var flowableJoin: Flowable<ChatMessage>
    private var flowableSend: Flowable<ChatMessage>
    private var flowableLeave: Flowable<ChatMessage>
    private var flowableBroadcast: Flowable<ChatMessage>

    init {
        chatClientService.observe().publish().apply {
            flowableJoin = filter{ Log.d("debug", "JOIN it.type: $it(${it.type == ChatMessage.MessageType.JOIN})");it.type == ChatMessage.MessageType.JOIN }
            flowableSend = filter{ Log.d("debug", "SEND it.type: $it(${it.type == ChatMessage.MessageType.SEND})");it.type == ChatMessage.MessageType.SEND }
            flowableLeave = filter{ Log.d("debug", "LEAVE it.type: $it(${it.type == ChatMessage.MessageType.SEND})");it.type == ChatMessage.MessageType.LEAVE }
            flowableBroadcast = filter{ Log.d("debug", "BROADCAST it.type: $it(${it.type == ChatMessage.MessageType.SEND})");it.type == ChatMessage.MessageType.BROADCAST }
            connect()
        }
        chatClientService.observe().subscribe { Log.d("debug", "observe: $it") }
    }

    private val sendPublisher: ReplaySubject<ChatMessage> = ReplaySubject.create()

    init {
        flowableWebsocketEvent
            .observeOn(Schedulers.io())
            .subscribe {
                if(it.javaClass.kotlin == WebSocket.Event.OnConnectionOpened::class) {
                    Log.d("debug", "regist")
                    sendPublisher
                        .observeOn(Schedulers.io())
                        .subscribe {
                            Log.d("debug", "SEND")
                            chatClientService.send(it)
                        }
                }
            }
    }

    override fun observeJoin(): Flowable<ChatMessage> = flowableJoin

    override fun observeSend(): Flowable<ChatMessage> = flowableSend

    override fun observeLeave(): Flowable<ChatMessage> = flowableLeave

    override fun observeBroadcast(): Flowable<ChatMessage> = flowableBroadcast

    override fun join() {
        sendPublisher.onNext(ChatMessage(ChatMessage.MessageType.JOIN, "", "", ""))
    }
}