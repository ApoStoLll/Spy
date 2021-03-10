package com.missclick.spy.ui.splash

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.missclick.spy.data.IRepository
import com.missclick.spy.data.Repository
import com.missclick.spy.data.local.SettingsRepository
import com.missclick.spy.data.models.WordModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch



class SplashViewModel(private val repository: IRepository,
                      private val settingsRepository: SettingsRepository)
    : ViewModel() {

    val ids = MutableLiveData<List<Long>>()
    val firstLaunch = settingsRepository.firstLaunch

    fun setFirstLaunch(status : Boolean){
        viewModelScope.launch {
            settingsRepository.setFirstLaunch(status)
        }
    }

    fun preloadDb(words : List<WordModel>){
        viewModelScope.launch {
            ids.value = words.map {
                async(Dispatchers.IO) {
                    repository.insertWord(it)
                }
            }.awaitAll()
        }
    }

}