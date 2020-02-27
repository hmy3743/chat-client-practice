package com.myhan.chat_client_practice.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MessagingService: Service() {
    private val repo: Repository by inject(named("service-repository"))
    private val handler: ClientHandler by inject()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        Log.d("debug", "onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("debug","onStartCommand")
        repo.observeJoin().observeOn(Schedulers.io()).subscribe{handler.onJoin(it)}
        repo.observeBroadcast().observeOn(Schedulers.io()).subscribe{handler.onBroadcast(it)}
        repo.observeLeave().observeOn(Schedulers.io()).subscribe{handler.onLeave(it)}
        repo.observeSend().observeOn(Schedulers.io()).subscribe{handler.onSend(it)}
        repo.join()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("debug", "onDestroy")
        super.onDestroy()
    }
}