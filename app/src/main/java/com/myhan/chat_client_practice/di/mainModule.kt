package com.myhan.chat_client_practice.di

import com.myhan.chat_client_practice.ActivityChattingViewModel
import com.myhan.chat_client_practice.FragmentChattingViewModel
import com.myhan.chat_client_practice.FragmentFriendViewModel
import com.myhan.chat_client_practice.RepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
    viewModel { FragmentChattingViewModel(get(named("repository"))) }
    viewModel { FragmentFriendViewModel(get(named("repository"))) }
    viewModel { ActivityChattingViewModel(get(named("repository"))) }

    single(named("repository")) { RepositoryImpl() }
}