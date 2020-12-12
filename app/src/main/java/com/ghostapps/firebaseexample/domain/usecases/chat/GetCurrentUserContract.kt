package com.ghostapps.firebaseexample.domain.usecases.chat

import com.google.firebase.auth.FirebaseUser

interface GetCurrentUserContract {
    fun execute(): FirebaseUser?
}