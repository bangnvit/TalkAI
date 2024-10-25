package com.bangnv.talkai.models.offline

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatMessage(
    val content: String,
    val isUser: Boolean
)
