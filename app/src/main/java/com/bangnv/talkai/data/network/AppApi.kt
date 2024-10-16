package com.bangnv.talkai.data.network

import com.bangnv.talkai.data.network.NetworkEndPoint.POST_REQUEST
import com.bangnv.talkai.models.request.ChatGPTRequest
import com.bangnv.talkai.models.response.ChatGPTResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AppApi {
    @POST(POST_REQUEST)
    suspend fun sendMessage(
        @Header("Authorization") auth: String,
        @Body request: ChatGPTRequest
    ): Response<ChatGPTResponse>
}