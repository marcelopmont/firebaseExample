package com.ghostapps.firebaseexample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val auth = Firebase.auth

        if (auth.currentUser != null) {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        loginSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        loginConfirm.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    startActivity(Intent(this, ChatActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Falha ao realizar login", Toast.LENGTH_SHORT).show()
                }
        }


    }

}