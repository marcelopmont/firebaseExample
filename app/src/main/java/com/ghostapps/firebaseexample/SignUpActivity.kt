package com.ghostapps.firebaseexample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val auth = Firebase.auth

        signUpConfirm.setOnClickListener {
            val name = signUpName.text.toString()
            val email = signUpEmail.text.toString()
            val password = signUpPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val currentUser = auth.currentUser

                    val userProfileChangeRequest = UserProfileChangeRequest.Builder().setDisplayName(name).build()

                    currentUser!!.updateProfile(userProfileChangeRequest)
                        .addOnCompleteListener {
                            startActivity(Intent(this, ChatActivity::class.java))
                        }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Falha ao realizar cadastro", Toast.LENGTH_SHORT).show()
                }
        }


    }


}