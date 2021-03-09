package com.missclick.spy.ui.cards

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.missclick.spy.data.IRepository
import kotlinx.coroutines.launch

class CardsViewModel(private val repository: IRepository) : ViewModel() {

    private val cardState = MutableLiveData<CardState>()

    fun getRandomWord(category : String) = liveData<String>{
        viewModelScope.launch {
            val words = repository.getWordsFromCategoryByName(nameOfCategory = category)
            words[(words.indices).random()]
        }
    }

    fun getSpy(players : Int) =
        (1..players).random()
}

sealed class CardState{
    object ClosedCard : CardState()
    class OpenedCard(val number : Int)
}