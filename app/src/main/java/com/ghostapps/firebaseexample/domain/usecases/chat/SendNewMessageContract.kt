package com.ghostapps.firebaseexample.domain.usecases.chat

import com.google.firebase.auth.FirebaseUser

interface SendNewMessageContract {
    fun execute(message: String, currentUser: FirebaseUser)
}