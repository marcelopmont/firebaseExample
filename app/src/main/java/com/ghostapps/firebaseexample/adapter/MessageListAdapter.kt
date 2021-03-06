package com.ghostapps.firebaseexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ghostapps.firebaseexample.R
import com.ghostapps.firebaseexample.model.Message

class MessageListAdapter : RecyclerView.Adapter<MessageListViewHolder>() {

    var list: ArrayList<Message> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_list, parent, false)
        return MessageListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        holder.bind(list[position])
    }

}