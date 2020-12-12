package com.ghostapps.firebaseexample.data.signUp.useCases

import com.ghostapps.firebaseexample.domain.helpers.DomainError
import com.ghostapps.firebaseexample.domain.usecases.signUp.MakeSignUpContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class MakeSignUp(
    private val auth: FirebaseAuth
) : MakeSignUpContract {

    override fun execute(
        name: String,
        email: String,
        password: String,
        onSuccessListener: () -> Unit,
        onFailureListener: (DomainError) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val currentUser = auth.currentUser

                val userProfileChangeRequest =
                    UserProfileChangeRequest.Builder().setDisplayName(name).build()

                currentUser!!.updateProfile(userProfileChangeRequest)
                    .addOnCompleteListener {
                        onSuccessListener()
                    }
            }
            .addOnFailureListener {
                onFailureListener(DomainError.GENERIC_ERROR)
            }
    }
}