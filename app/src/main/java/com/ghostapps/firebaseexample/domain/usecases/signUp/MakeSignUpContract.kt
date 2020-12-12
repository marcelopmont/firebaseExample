package com.ghostapps.firebaseexample.domain.usecases.signUp

import com.ghostapps.firebaseexample.domain.helpers.DomainError

interface MakeSignUpContract {
    fun execute(
        name: String,
        email: String,
        password: String,
        onSuccessListener: () -> Unit,
        onFailureListener: (DomainError) -> Unit
    )
}