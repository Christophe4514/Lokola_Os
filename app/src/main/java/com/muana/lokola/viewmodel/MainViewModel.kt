package com.muana.lokola.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muana.lokola.util.LanguageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val languageManager: LanguageManager
) : ViewModel() {

    private val _isFirstLaunch = MutableStateFlow(true)
    val isFirstLaunch: StateFlow<Boolean> = _isFirstLaunch.asStateFlow()

    private val _currentLanguage = MutableStateFlow("fr")
    val currentLanguage: StateFlow<String> = _currentLanguage.asStateFlow()

    init {
        viewModelScope.launch {
            languageManager.currentLanguage.collect { language ->
                _currentLanguage.value = language
            }
        }
    }

    fun markOnboardingCompleted() {
        _isFirstLaunch.value = false
    }

    fun changeLanguage(language: String) {
        viewModelScope.launch {
            languageManager.setLanguage(language)
        }
    }
}
