package com.ghostapps.firebaseexample.domain.usecases.chat

import com.ghostapps.firebaseexample.domain.entities.MessageEntity
import com.google.firebase.auth.FirebaseUser

interface GetChatMessagesContract {
    fun execute(currentUser: FirebaseUser, onResult: (ArrayList<MessageEntity>) -> Unit)
}