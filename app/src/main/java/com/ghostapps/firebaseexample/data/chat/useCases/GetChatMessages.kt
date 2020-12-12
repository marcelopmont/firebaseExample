package com.ghostapps.firebaseexample.data.chat.useCases

import com.ghostapps.firebaseexample.domain.entities.MessageEntity
import com.ghostapps.firebaseexample.domain.usecases.chat.GetChatMessagesContract
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class GetChatMessages(
    private val db: FirebaseFirestore
): GetChatMessagesContract {

    override fun execute(currentUser: FirebaseUser, onResult: (ArrayList<MessageEntity>) -> Unit) {
        db.collection("chats")
            .addSnapshotListener { value, _ ->
                val messagesList = arrayListOf<MessageEntity>()
                for(document in value!!) {
                    val message = MessageEntity(
                        userId = document["userId"].toString(),
                        userName = document["userName"].toString(),
                        date = document["date"] as Long,
                        text = document["text"].toString(),
                        messageFromCurrentUser = currentUser.uid == document["userId"].toString()
                    )
                    messagesList.add(message)
                }
                onResult(messagesList)
            }
    }

}