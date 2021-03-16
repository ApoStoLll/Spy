package com.missclick.spy.ui.sets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.missclick.spy.data.IRepository
import com.missclick.spy.data.local.SettingsRepository
import com.missclick.spy.data.models.WordListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch
import java.lang.Exception

class SetsViewModel(
        private val repository: IRepository
) : ViewModel() {

    fun getSets() = liveData<List<String>>(Dispatchers.IO) {
        try {
            emit(repository.getSets())
        } catch (e : Exception){
            e.printStackTrace()
        }

    }
}
