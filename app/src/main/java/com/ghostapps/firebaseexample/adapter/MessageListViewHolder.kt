package com.ghostapps.firebaseexample.adapter

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ghostapps.firebaseexample.model.Message
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.item_message_list.view.*

class MessageListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(message: Message) {
        itemView.itemMessageText.text = message.text
        itemView.itemMessageUserName.text = message.userName

        if (Firebase.auth.currentUser?.uid == message.userId) {
            itemView.itemMessageContainer.gravity = Gravity.END
        } else {
            itemView.itemMessageContainer.gravity = Gravity.START
        }
    }
}