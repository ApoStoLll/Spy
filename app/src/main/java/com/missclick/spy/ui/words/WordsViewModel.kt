package com.missclick.spy.ui.words

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.missclick.spy.data.IRepository
import com.missclick.spy.data.local.SettingsRepository
import com.missclick.spy.data.models.WordListModel
import com.missclick.spy.data.models.WordsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.lang.Exception

class WordsViewModel(
        private val repository: IRepository,private val settingsRepository: SettingsRepository
) : ViewModel() {

    val set = settingsRepository.set

    fun getWords(setName : String) = liveData<List<String>>(Dispatchers.IO) {
        try {
            emit(repository.getWordsFromCategoryByName(setName))
        } catch (e : Exception){
            e.printStackTrace()
        }

    }

    fun update(oldSetName : String, newSetName : String,data : List<WordListModel>){
        viewModelScope.launch {
            settingsRepository.setSet(newSetName)
        }
        removeWordsInCategory(oldSetName)
        addWords(data,newSetName)
    }

    private fun addWords(words : List<WordListModel>, category : String){
        viewModelScope.launch(Dispatchers.IO) {
            words.map {
                async { repository.insertWord(WordsModel(word = it.word, category = category)) }
            }.awaitAll()
        }
    }

    private fun removeWordsInCategory(category: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeWordInCategory(category)
        }
    }

    fun removeWord(word : WordListModel, category : String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeWord(WordsModel(word = word.word, category = category))
        }
    }
}