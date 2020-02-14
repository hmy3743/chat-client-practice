package com.myhan.chat_client_practice.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myhan.chat_client_practice.FragmentFriendViewModel
import com.myhan.chat_client_practice.R
import com.myhan.chat_client_practice.model.User
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_recyclerviewchatroom.view.*
import java.io.File

class RecyclerViewAdapterFriend(private val lifecycleOwner: LifecycleOwner, private val viewModel: FragmentFriendViewModel): RecyclerView.Adapter<RecyclerViewAdapterFriend.ViewHolder>() {
    inner class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerviewfriend, parent, false)
    ) {
        fun bind(image: File?, title: String, lastContent: String) {
            if(image != null && image.exists()) {
                Glide.with(itemView.imageView).load(image).into(itemView.imageView)
            }
            itemView.textViewName.text = title
            itemView.textViewComment.text = lastContent
        }
    }

    var friends: List<User> = listOf()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        viewModel.users()
            .observe(lifecycleOwner, object: Observer<List<User>> {
                override fun onChanged(updateData: List<User>?) {
                    if(updateData != null) {
                        friends = updateData
                        notifyDataSetChanged()
                    }
                }
            })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return friends.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(null, friends[position].nickname, friends[position].comment)
    }
}