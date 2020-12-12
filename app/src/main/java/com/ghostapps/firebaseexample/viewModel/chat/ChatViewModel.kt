package com.ghostapps.firebaseexample.viewModel.chat

import androidx.lifecycle.MutableLiveData
import com.ghostapps.firebaseexample.domain.entities.MessageEntity
import com.ghostapps.firebaseexample.domain.usecases.chat.GetChatMessagesContract
import com.ghostapps.firebaseexample.domain.usecases.chat.GetCurrentUserContract
import com.ghostapps.firebaseexample.domain.usecases.chat.SendNewMessageContract
import com.ghostapps.firebaseexample.viewModel.BaseViewModel

class ChatViewModel(
    private val getCurrentUser: GetCurrentUserContract,
    private val sendNewMessage: SendNewMessageContract,
    private val getChatMessages: GetChatMessagesContract
) : BaseViewModel() {

    val messages = MutableLiveData<ArrayList<MessageEntity>>()

    var userInputText = ""

    fun onCreate() {
        getCurrentUser.execute()?.let { user ->
            getChatMessages.execute(user) { messages ->
                messages.sortBy { it.date }
                this.messages.postValue(messages)
            }
        }
    }

    fun onSendMessagePressed() {
        getCurrentUser.execute()?.let {
            sendNewMessage.execute(userInputText, it)
            userInputText = ""
            notifyChange()
        }
    }
}