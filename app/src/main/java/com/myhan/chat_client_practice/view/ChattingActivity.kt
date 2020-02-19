package com.myhan.chat_client_practice.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.myhan.chat_client_practice.ActivityChattingViewModel
import com.myhan.chat_client_practice.R
import com.myhan.chat_client_practice.databinding.ActivityChattingBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ChattingActivity : AppCompatActivity() {

    val viewModel: ActivityChattingViewModel by viewModel()
    lateinit var roomId: String
    lateinit var binding: ActivityChattingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting)
        roomId = intent.getStringExtra(EXTRA_KEY_ROOM_ID) ?: ""

        viewModel.bindRoom(roomId)

        binding.vm = viewModel
        binding.recyclerViewMessage.adapter = RecyclerViewAdapterMessage(this, viewModel)
        binding.recyclerViewMessage.layoutManager = LinearLayoutManager(this)
        binding.lifecycleOwner = this
    }

    companion object {
        val EXTRA_KEY_ROOM_ID = "EXTRA_KEY_ROOM_ID"
    }
}
