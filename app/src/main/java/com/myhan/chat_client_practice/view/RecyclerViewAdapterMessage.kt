package com.myhan.chat_client_practice.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.myhan.chat_client_practice.ActivityChattingViewModel
import com.myhan.chat_client_practice.R
import com.myhan.chat_client_practice.model.ChatMessage
import com.myhan.chat_client_practice.model.User
import kotlinx.android.synthetic.main.item_recyclerviewmessage_from_me.view.*

class RecyclerViewAdapterMessage(private val lifecycleOwner: LifecycleOwner, private val viewModel: ActivityChattingViewModel): RecyclerView.Adapter<RecyclerViewAdapterMessage.ViewHolder>() {
    class ViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(RecyclerViewAdapterMessage.VIEWS[viewType], parent, false)
    ) {
        fun bind(message: ChatMessage) {
            itemView.bubbleTextViewMessageContent.text = message.message
        }
    }

    companion object {
        val VIEWS = listOf<Int>(
            R.layout.item_recyclerviewmessage_from_me,
            R.layout.item_recyclerviewmessage_from_other
        )
    }

    private lateinit var myProfile: User
    private var messages: List<ChatMessage> = listOf()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        viewModel.me()
            .observe(
                lifecycleOwner,
                Observer {
                    myProfile = it
                    viewModel.messages()
                        .observe(
                            lifecycleOwner,
                            Observer<List<ChatMessage>> { updateData ->
                                if(updateData != null) {
                                    messages = updateData
                                    notifyDataSetChanged()
                                }
                            })
                }
            )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return messages.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when(messages[position].sender) {
            myProfile.userId -> 0
            else -> 1
        }
    }
}