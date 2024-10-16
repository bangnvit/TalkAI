package com.bangnv.talkai.data.repositories

import android.util.Log
import com.bangnv.talkai.BuildConfig
import com.bangnv.talkai.data.network.RetrofitClient
import com.bangnv.talkai.models.request.ChatGPTRequest
import com.bangnv.talkai.models.request.Message
import com.bangnv.talkai.models.response.ChatGPTResponse
import com.bangnv.talkai.utils.Constants

class ChatGPTRepository {

    suspend fun getChatGptResponse(message: String): String {
        val request = ChatGPTRequest(
            model = Constants.GPT_3_5_TURBO,
            messages = listOf(Message(role = Constants.DEFAULT_CHATGPT_ROLE, content = message))
        )

        Log.d("API Key", BuildConfig.OPENAI_API_KEY)

        return try {
            val response =
                RetrofitClient.instance.sendMessage("Bearer ${BuildConfig.OPENAI_API_KEY}", request)

            if (response.isSuccessful) {
                val chatResponse: ChatGPTResponse? = response.body()
                chatResponse?.choices?.firstOrNull()?.message?.content ?: "Error: isSuccessful but null"
            } else {
                "Error: response is not successful. Status Code: ${response.code()}"
            }
        } catch (e: Exception) {
            "Exception: ${e.message}"
        }
    }
}

