package com.ghostapps.firebaseexample.main.di

import com.ghostapps.firebaseexample.data.chat.useCases.GetChatMessages
import com.ghostapps.firebaseexample.data.chat.useCases.GetCurrentUser
import com.ghostapps.firebaseexample.data.chat.useCases.SendNewMessage
import com.ghostapps.firebaseexample.data.login.useCases.CheckUserIsLogged
import com.ghostapps.firebaseexample.data.login.useCases.MakeLogin
import com.ghostapps.firebaseexample.data.signUp.useCases.MakeSignUp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

object DataModules {
    val modules = module {
        factory { CheckUserIsLogged(Firebase.auth) }
        factory { MakeLogin(Firebase.auth) }

        factory { MakeSignUp(Firebase.auth) }

        factory { GetCurrentUser(Firebase.auth) }
        factory { SendNewMessage(FirebaseFirestore.getInstance()) }
        factory { GetChatMessages(FirebaseFirestore.getInstance()) }

    }
}