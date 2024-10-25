package com.bangnv.talkai.models.response

import com.bangnv.talkai.models.request.Message
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Choice(
    val message: Message
)
