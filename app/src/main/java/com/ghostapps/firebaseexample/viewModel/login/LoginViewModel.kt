package com.ghostapps.firebaseexample.viewModel.login

import androidx.lifecycle.ViewModel
import com.ghostapps.firebaseexample.domain.helpers.DomainError
import com.ghostapps.firebaseexample.domain.usecases.login.CheckUserIsLoggedContract
import com.ghostapps.firebaseexample.domain.usecases.login.MakeLoginContract

class LoginViewModel(
    private val contract: LoginContract,
    private val checkUserIsLogged: CheckUserIsLoggedContract,
    private val makeLogin: MakeLoginContract
) : ViewModel() {

    var email: String = ""
    var password: String = ""

    fun onCreate() {
        if (checkUserIsLogged.execute()) {
            contract.goToMainActivity()
        }
    }

    fun onLoginPressed() {
        makeLogin.execute(email, password, {
            contract.goToMainActivity()
        }, { error ->
            when (error) {
                DomainError.GENERIC_ERROR -> contract.showGenericErrorMessage()
            }
        })

    }

    fun onSignUpPressed() {
        contract.goToSignUpActivity()
    }

}