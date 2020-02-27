package com.myhan.chat_client_practice.di

import com.myhan.chat_client_practice.ActivityChattingViewModel
import com.myhan.chat_client_practice.FragmentChattingViewModel
import com.myhan.chat_client_practice.FragmentFriendViewModel
import com.myhan.chat_client_practice.RepositoryImpl
import com.myhan.chat_client_practice.service.ClientHandler
import com.myhan.chat_client_practice.service.network.ChatClientService
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
    viewModel { FragmentChattingViewModel(get(named("repository"))) }
    viewModel { FragmentFriendViewModel(get(named("repository"))) }
    viewModel { ActivityChattingViewModel(get(named("repository"))) }

    single(named("repository")) { RepositoryImpl() }
    single(named("service-repository")) { com.myhan.chat_client_practice.service.RepositoryImpl(get()) }
    single { ClientHandler() }
    single{
        OkHttpClient.Builder()
            .build()
    }
    single{
        Scarlet.Builder()
            .webSocketFactory(get<OkHttpClient>().newWebSocketFactory("ws://192.168.2.3:8080/ws"))
            .addMessageAdapterFactory(MoshiMessageAdapter.Factory())
            .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
            .build()
    }
    single{
        get<Scarlet>().create(ChatClientService::class.java)
    }
}