package com.myhan.chat_client_practice.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myhan.chat_client_practice.FragmentChattingViewModel
import com.myhan.chat_client_practice.R
import com.myhan.chat_client_practice.databinding.FragmentChattingBinding
import org.koin.android.viewmodel.ext.android.viewModel


class FragmentChatting : Fragment() {

    companion object {
        fun newInstance() =
            FragmentChatting()
    }

    private val viewModel: FragmentChattingViewModel by viewModel()
    private lateinit var binding: FragmentChattingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_chatting, container, false)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        binding.recyclerViewChatRoom.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewChatRoom.adapter =
            RecyclerViewAdapterChatRoom(activity as Activity, this, viewModel)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
