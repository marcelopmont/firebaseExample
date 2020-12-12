package com.ghostapps.firebaseexample.domain.entities

class MessageEntity (
    val userId: String,
    val userName: String?,
    val text: String,
    val date: Long,
    val messageFromCurrentUser: Boolean
)