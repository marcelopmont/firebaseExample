package com.ghostapps.firebaseexample.viewModel

import com.ghostapps.firebaseexample.domain.helpers.DomainError
import com.ghostapps.firebaseexample.domain.usecases.login.CheckUserIsLoggedContract
import com.ghostapps.firebaseexample.domain.usecases.login.MakeLoginContract
import com.ghostapps.firebaseexample.viewModel.login.LoginContract
import com.ghostapps.firebaseexample.viewModel.login.LoginViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.times
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginViewModelTest {

    var sut: LoginViewModel? = null

    private val contract = Mockito.mock(LoginContract::class.java)
    private val checkUserIsLogged = Mockito.mock(CheckUserIsLoggedContract::class.java)
    private val makeLogin = Mockito.mock(MakeLoginContract::class.java)


    @Before
    fun setup() {
        sut = LoginViewModel(
            contract,
            checkUserIsLogged,
            makeLogin
        )
    }

    @Test
    fun `Should call go to main activity if user is logged`() {
        Mockito.`when`(checkUserIsLogged.execute()).thenAnswer {
            return@thenAnswer true
        }

        sut?.onCreate()

        Mockito.verify(contract, times(1)).goToMainActivity()
    }

    @Test
    fun `Should not call go to main activity if user is logged`() {
        Mockito.`when`(checkUserIsLogged.execute()).thenAnswer {
            return@thenAnswer false
        }

        sut?.onCreate()

        Mockito.verify(contract, times(0)).goToMainActivity()
    }

    @Test
    fun `Should call make login with correct parameters when button is pressed`() {
        sut?.email = "teste@gmail.com"
        sut?.password = "password123"

        sut?.onLoginPressed()

        Mockito.verify(makeLogin, times(1)).execute(eq("teste@gmail.com"), eq("password123"), any(), any())
    }

    @Test
    fun `Should call go to main activity if make login returns success`() {

        Mockito.`when`(makeLogin.execute(any(), any(), any(), any())).then { invocation ->
            (invocation.arguments[2] as () -> Unit).invoke()
        }

        sut?.onLoginPressed()

        Mockito.verify(contract, times(1)).goToMainActivity()
    }

    @Test
    fun `Should call show generic error if make login returns failure`() {

        Mockito.`when`(makeLogin.execute(any(), any(), any(), any())).then { invocation ->
            (invocation.arguments[3] as (DomainError) -> Unit).invoke(DomainError.GENERIC_ERROR)
        }

        sut?.onLoginPressed()

        Mockito.verify(contract, times(1)).showGenericErrorMessage()
    }

    @Test
    fun `Should call go to SignUp activity if button is pressed`() {
        sut?.onSignUpPressed()

        Mockito.verify(contract, times(1)).goToSignUpActivity()
    }


}