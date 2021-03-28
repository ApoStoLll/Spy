package com.missclick.spy.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.missclick.spy.data.IRepository
import com.missclick.spy.data.Repository
import com.missclick.spy.data.local.SettingsRepository
import com.missclick.spy.utills.LocalLanguage
import kotlinx.coroutines.launch
import java.util.*

class SettingsViewModel(private val settingsRepository: SettingsRepository,
                        private val repository: IRepository) : ViewModel() {

    val language = settingsRepository.language

    fun setLanguage(language: LocalLanguage){
        viewModelScope.launch {
            val lang = LocalLanguage.mapLangToString(language)
            settingsRepository.setLanguage(lang)
            updateDB()
        }
    }

    private suspend fun updateDB(){

        repository.removeWordInCategory()
    }
}