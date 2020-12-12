package com.ghostapps.firebaseexample.data.chat.useCases

import com.ghostapps.firebaseexample.domain.usecases.chat.GetCurrentUserContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class GetCurrentUser(
    private val auth: FirebaseAuth
): GetCurrentUserContract {

    override fun execute(): FirebaseUser? {
        return auth.currentUser
    }
}