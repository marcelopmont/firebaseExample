package com.ghostapps.firebaseexample.domain.usecases.login

import com.ghostapps.firebaseexample.domain.helpers.DomainError

interface MakeLoginContract {
    fun execute(email: String,
                password: String,
                onSuccessListener: () -> Unit,
                onFailureListener: (DomainError) -> Unit)
}