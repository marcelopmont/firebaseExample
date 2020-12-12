package com.ghostapps.firebaseexample.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ghostapps.firebaseexample.R
import com.ghostapps.firebaseexample.databinding.ActivityLoginBinding
import com.ghostapps.firebaseexample.ui.signUp.SignUpActivity
import com.ghostapps.firebaseexample.ui.chat.ChatActivity
import com.ghostapps.firebaseexample.viewModel.login.LoginContract
import com.ghostapps.firebaseexample.viewModel.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), LoginContract {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel

        viewModel.onCreate()
    }

    override fun showGenericErrorMessage() {
        Toast.makeText(this, "Falha ao realizar login", Toast.LENGTH_SHORT).show()
    }

    override fun goToMainActivity() {
        startActivity(Intent(this, ChatActivity::class.java))
        finish()
    }

    override fun goToSignUpActivity() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

}