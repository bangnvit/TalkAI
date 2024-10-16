package com.bangnv.talkai.ui.talk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangnv.talkai.data.repositories.ChatGPTRepository
import com.bangnv.talkai.data.repositories.GeminiRepository
import com.bangnv.talkai.models.offline.ChatMessage

class TalkViewModel(
    private val chatGPTRepository: ChatGPTRepository,
    private val geminiRepository: GeminiRepository
) : ViewModel() {

    private val _messages = MutableLiveData<List<ChatMessage>>()
    val messages: LiveData<List<ChatMessage>> get() = _messages

    init {
        _messages.value = emptyList()
    }

    suspend fun sendMessage(message: String, useGemini: Boolean) {
        val currentMessages = _messages.value ?: emptyList()
        val userMessage = ChatMessage(content = message, isUser = true)
        _messages.value = currentMessages + userMessage

        // Call true API using flag useGemini
        val response = if (useGemini) {
            geminiRepository.getGeminiResponse(message)  // Call Gemini API
        } else {
            chatGPTRepository.getChatGptResponse(message)  // Call ChatGPT API
        }

        val botMessage = ChatMessage(content = response, isUser = false)
        _messages.value = _messages.value?.plus(botMessage)
    }
}
