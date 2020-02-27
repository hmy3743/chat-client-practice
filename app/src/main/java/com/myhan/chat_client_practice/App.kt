package com.myhan.chat_client_practice

import android.app.Application
import android.content.Intent
import com.myhan.chat_client_practice.di.mainModule
import com.myhan.chat_client_practice.service.MessagingService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(mainModule)
        }
        startService(Intent(applicationContext, MessagingService::class.java))
    }
}