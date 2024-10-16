package com.bangnv.talkai.ui.vocabulary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VocabularyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Vocabulary Fragment"
    }
    val text: LiveData<String> = _text
}