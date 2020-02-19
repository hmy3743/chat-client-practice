package com.myhan.chat_client_practice.view

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myhan.chat_client_practice.FragmentChattingViewModel
import com.myhan.chat_client_practice.R
import com.myhan.chat_client_practice.model.ChatRoom
import kotlinx.android.synthetic.main.item_recyclerviewchatroom.view.*
import java.io.File

class RecyclerViewAdapterChatRoom(private val activity: Activity, private val lifecycleOwner: LifecycleOwner, private val viewModel: FragmentChattingViewModel): RecyclerView.Adapter<RecyclerViewAdapterChatRoom.ViewHolder>() {
    inner class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerviewchatroom, parent, false)
    ) {
        fun bind(image: File?, roomId: String, title: String, lastContent: String, participantCount: Int) {
            if(image != null && image.exists()) {
                Glide.with(itemView.imageView).load(image).into(itemView.imageView)
            }
            itemView.textViewName.text = title
            itemView.textViewComment.text = lastContent
            itemView.textViewParticipantCount.text = participantCount.toString()

            itemView.setOnClickListener {
                val intent = Intent(activity, ChattingActivity::class.java)
                intent.putExtra(ChattingActivity.EXTRA_KEY_ROOM_ID, roomId)
                activity.startActivity(intent)
            }
        }
    }

    var chatRooms: List<ChatRoom> = listOf()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        viewModel.chatRooms()
            .observe(lifecycleOwner, object: Observer<List<ChatRoom>> {
                override fun onChanged(updateData: List<ChatRoom>?) {
                    if(updateData != null) {
                        chatRooms = updateData
                        notifyDataSetChanged()
                    }
                }
            })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return chatRooms.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(null, chatRooms[position].roomId, chatRooms[position].name, "lastContent", 2)
    }
}