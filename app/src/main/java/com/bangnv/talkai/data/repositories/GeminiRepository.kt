package com.bangnv.talkai.data.repositories

import com.bangnv.talkai.BuildConfig
import com.bangnv.talkai.utils.Constants
import com.google.ai.client.generativeai.GenerativeModel

class GeminiRepository {

    suspend fun getGeminiResponse(prompt: String): String {
        return try {
            val generativeModel = GenerativeModel(
                modelName = Constants.GEMINI_MODEL,
                apiKey = BuildConfig.GEMINI_API_KEY
            )
            val response = generativeModel.generateContent(prompt)
            response.text ?: "Error: Response is null"
        } catch (e: Exception) {
            "Exception: ${e.message}"
        }
    }
}