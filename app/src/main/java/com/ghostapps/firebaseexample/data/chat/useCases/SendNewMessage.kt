package com.ghostapps.firebaseexample.data.chat.useCases

import com.ghostapps.firebaseexample.data.chat.model.MessageModel
import com.ghostapps.firebaseexample.domain.usecases.chat.SendNewMessageContract
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class SendNewMessage(
    private val db: FirebaseFirestore
): SendNewMessageContract {

    override fun execute(message: String, currentUser: FirebaseUser) {
        db.collection("chats")
            .add(MessageModel(
                userName = currentUser.displayName,
                userId = currentUser.uid,
                text = message,
                date = Date().time
            ))
    }

}