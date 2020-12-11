package com.ghostapps.firebaseexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghostapps.firebaseexample.adapter.MessageListAdapter
import com.ghostapps.firebaseexample.model.Message
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*

class ChatActivity : AppCompatActivity() {

    val adapter = MessageListAdapter()

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chatMessages.adapter = adapter
        chatMessages.layoutManager = LinearLayoutManager(this)


        chatSendButton.setOnClickListener {
            val message = chatSendMessage.text.toString()

            sendMessage(message)

            chatSendMessage.setText("")
        }

        getMessages()
    }


    private fun sendMessage(message: String) {
        val currentUser = Firebase.auth.currentUser!!

        val messageModel = Message(
            userId = currentUser.uid,
            userName = currentUser.displayName,
            date = Date().time,
            text = message
        )

        db.collection("chats")
            .add(messageModel)
    }

    private fun getMessages() {
        db.collection("chats")
            .addSnapshotListener { value, _ ->
                val messagesList = arrayListOf<Message>()
                for(document in value!!) {
                    val message = Message(
                        userId = document["userId"].toString(),
                        userName = document["userName"].toString(),
                        date = document["date"] as Long,
                        text = document["text"].toString()
                    )
                    messagesList.add(message)
                }
                messagesList.sortBy { it.date }

                adapter.list = messagesList
                adapter.notifyDataSetChanged()

                chatMessages.scrollToPosition(messagesList.size - 1)
            }
    }


}