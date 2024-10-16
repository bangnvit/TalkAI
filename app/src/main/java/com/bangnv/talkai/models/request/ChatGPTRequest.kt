package com.bangnv.talkai.models.request

data class ChatGPTRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>
)
