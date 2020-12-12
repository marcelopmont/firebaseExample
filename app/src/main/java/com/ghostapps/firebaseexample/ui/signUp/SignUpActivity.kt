package com.ghostapps.firebaseexample.ui.signUp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ghostapps.firebaseexample.R
import com.ghostapps.firebaseexample.databinding.ActivityLoginBinding
import com.ghostapps.firebaseexample.databinding.ActivitySignUpBinding
import com.ghostapps.firebaseexample.ui.chat.ChatActivity
import com.ghostapps.firebaseexample.viewModel.login.LoginViewModel
import com.ghostapps.firebaseexample.viewModel.signUp.SignUpContract
import com.ghostapps.firebaseexample.viewModel.signUp.SignUpViewModel
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SignUpActivity : AppCompatActivity(), SignUpContract {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.viewModel = viewModel
    }

    override fun showGenericErrorMessage() {
        Toast.makeText(this, "Falha ao realizar login", Toast.LENGTH_SHORT).show()
    }

    override fun goToMainActivity() {
        startActivity(Intent(this, ChatActivity::class.java))
    }


}