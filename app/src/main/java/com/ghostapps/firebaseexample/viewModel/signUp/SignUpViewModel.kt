package com.ghostapps.firebaseexample.viewModel.signUp

import androidx.lifecycle.ViewModel
import com.ghostapps.firebaseexample.domain.usecases.signUp.MakeSignUpContract

class SignUpViewModel(
    private val contract: SignUpContract,
    private val makeSignUp: MakeSignUpContract
) : ViewModel() {

    var name = ""
    var email = ""
    var password = ""

    fun onSignUpPressed() {
        makeSignUp.execute(
            name,
            email,
            password,
            {
                contract.goToMainActivity()
            },
            { error ->
                contract.showGenericErrorMessage()
            }
        )
    }
}