package com.bangnv.talkai.models.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatGPTRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>
)
