package com.missclick.spy.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.missclick.spy.data.IRepository
import com.missclick.spy.data.local.SettingsRepository
import com.missclick.spy.data.models.WordsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

    fun preloadDb(collections : List<WordsModel>){
        viewModelScope.launch {
            ids.value = collections.map {
                async(Dispatchers.IO) {
                    repository.insertWord(it)
                }
            }.awaitAll()
        }
    }

}