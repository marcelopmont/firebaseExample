package com.ghostapps.firebaseexample.ui.chat.adapter

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ghostapps.firebaseexample.domain.entities.MessageEntity
import kotlinx.android.synthetic.main.item_message_list.view.*

class MessageListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(message: MessageEntity) {
        itemView.itemMessageText.text = message.text
        itemView.itemMessageUserName.text = message.userName

        if (message.messageFromCurrentUser) {
            itemView.itemMessageContainer.gravity = Gravity.END
        } else {
            itemView.itemMessageContainer.gravity = Gravity.START
        }
    }
}