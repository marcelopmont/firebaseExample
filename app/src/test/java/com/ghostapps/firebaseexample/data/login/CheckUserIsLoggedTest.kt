package com.ghostapps.firebaseexample.data.login

import com.ghostapps.firebaseexample.data.login.useCases.CheckUserIsLogged
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CheckUserIsLoggedTest {

    var sut: CheckUserIsLogged? = null
    private val firebaseAuth: FirebaseAuth = Mockito.mock(FirebaseAuth::class.java)
    private val firebaseUser: FirebaseUser = Mockito.mock(FirebaseUser::class.java)

    private fun mockFirebaseAuthSuccess() {
        Mockito.`when`(firebaseAuth.currentUser).thenAnswer {
            return@thenAnswer firebaseUser
        }
    }

    private fun mockFirebaseAuthFailure() {
        Mockito.`when`(firebaseAuth.currentUser).thenAnswer {
            return@thenAnswer null
        }
    }

    @Before
    fun setup() {
        sut = CheckUserIsLogged(firebaseAuth)
        mockFirebaseAuthSuccess()
    }

    @Test
    fun `Should return true is firebase auth user is not null`() {
        val result = sut?.execute()
        assert(result == true)
    }
    @Test
    fun `Should return false is firebase auth user is null`() {
        mockFirebaseAuthFailure()

        val result = sut?.execute()
        assert(result == false)
    }
}