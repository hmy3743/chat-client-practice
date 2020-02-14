package com.myhan.chat_client_practice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myhan.chat_client_practice.FragmentFriendViewModel
import com.myhan.chat_client_practice.R
import com.myhan.chat_client_practice.databinding.FragmentFriendBinding
import org.koin.android.viewmodel.ext.android.viewModel


class FragmentFriend : Fragment() {

    companion object {
        fun newInstance() = FragmentFriend()
    }

    private val viewModel: FragmentFriendViewModel by viewModel()
    private lateinit var binding: FragmentFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_friend, container, false)

        binding.lifecycleOwner = this
        binding.vm = viewModel
        binding.recyclerViewFriend.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewFriend.adapter = RecyclerViewAdapterFriend(this, viewModel)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
