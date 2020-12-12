package com.ghostapps.firebaseexample.domain.usecases.login

interface CheckUserIsLoggedContract {
    fun execute(): Boolean
}