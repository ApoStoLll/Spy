package com.missclick.spy.ui.words

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.missclick.spy.data.IRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class WordsViewModel(
        private val repository: IRepository
) : ViewModel() {

    fun getWords(setName : String) = liveData<List<String>>(Dispatchers.IO) {
        try {
            emit(repository.getWordsFromCategoryByName(setName))
        } catch (e : Exception){
            e.printStackTrace()
        }

    }
}