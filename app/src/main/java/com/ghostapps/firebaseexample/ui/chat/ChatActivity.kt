package com.ghostapps.firebaseexample.ui.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghostapps.firebaseexample.R
import com.ghostapps.firebaseexample.databinding.ActivityChatBinding
import com.ghostapps.firebaseexample.ui.chat.adapter.MessageListAdapter
import com.ghostapps.firebaseexample.viewModel.chat.ChatViewModel
import kotlinx.android.synthetic.main.activity_chat.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChatActivity : AppCompatActivity() {

    val adapter = MessageListAdapter()

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        binding.viewModel = viewModel

        viewModel.onCreate()

        setupView()
        setupViewModel()
    }

    private fun setupView() {
        chatMessages.adapter = adapter
        chatMessages.layoutManager = LinearLayoutManager(this)
    }

    private fun setupViewModel() {
        viewModel.messages.observe(this, Observer {
            adapter.list = it
            adapter.notifyDataSetChanged()
            chatMessages.scrollToPosition(it.size - 1)
        })
    }
}