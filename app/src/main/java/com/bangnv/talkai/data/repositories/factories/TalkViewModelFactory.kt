package com.bangnv.talkai.data.repositories.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangnv.talkai.data.repositories.ChatGPTRepository
import com.bangnv.talkai.data.repositories.GeminiRepository
import com.bangnv.talkai.ui.talk.TalkViewModel

class TalkViewModelFactory(
    private val chatGPTRepository: ChatGPTRepository,
    private val geminiRepository: GeminiRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TalkViewModel::class.java)) {
            return TalkViewModel(chatGPTRepository, geminiRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
