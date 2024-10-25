package com.bangnv.talkai.models.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatGPTResponse(
    val choices: List<Choice>
)
