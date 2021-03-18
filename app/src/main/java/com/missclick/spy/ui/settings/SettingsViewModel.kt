package com.missclick.spy.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.missclick.spy.data.local.SettingsRepository
import com.missclick.spy.data.models.GameParams
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language

class SettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {

    val language = settingsRepository.language

    fun setLanguage(language : String){
        viewModelScope.launch {
            settingsRepository.setLanguage(language)
        }
    }
}