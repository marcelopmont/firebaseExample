package com.ghostapps.firebaseexample.data.login.useCases

import com.ghostapps.firebaseexample.domain.usecases.login.CheckUserIsLoggedContract
import com.google.firebase.auth.FirebaseAuth

class CheckUserIsLogged(
    private val auth: FirebaseAuth
) : CheckUserIsLoggedContract {

    override fun execute(): Boolean {
        return auth.currentUser != null
    }

}