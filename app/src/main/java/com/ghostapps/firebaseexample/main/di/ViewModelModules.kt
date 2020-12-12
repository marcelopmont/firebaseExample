package com.ghostapps.firebaseexample.main.di

import com.ghostapps.firebaseexample.data.chat.useCases.GetChatMessages
import com.ghostapps.firebaseexample.data.chat.useCases.GetCurrentUser
import com.ghostapps.firebaseexample.data.chat.useCases.SendNewMessage
import com.ghostapps.firebaseexample.data.login.useCases.CheckUserIsLogged
import com.ghostapps.firebaseexample.data.login.useCases.MakeLogin
import com.ghostapps.firebaseexample.data.signUp.useCases.MakeSignUp
import com.ghostapps.firebaseexample.viewModel.chat.ChatViewModel
import com.ghostapps.firebaseexample.viewModel.login.LoginContract
import com.ghostapps.firebaseexample.viewModel.login.LoginViewModel
import com.ghostapps.firebaseexample.viewModel.signUp.SignUpContract
import com.ghostapps.firebaseexample.viewModel.signUp.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModules {

    val modules = module {
        viewModel { (contract: LoginContract) ->
            LoginViewModel(contract, get<CheckUserIsLogged>(), get<MakeLogin>()) }

        viewModel { (contract: SignUpContract) ->
            SignUpViewModel(contract, get<MakeSignUp>())
        }

        viewModel { ChatViewModel(
            get<GetCurrentUser>(),
            get<SendNewMessage>(),
            get<GetChatMessages>()
        ) }
    }

}