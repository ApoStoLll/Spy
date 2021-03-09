package com.missclick.spy.ui.cards

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.missclick.spy.data.IRepository
import kotlinx.coroutines.launch

class CardsViewModel(private val repository: IRepository) : ViewModel() {

    val cardState = MutableLiveData<CardState>(CardState.ClosedCard(1))


    fun getRandomWord(category : String) = liveData<String>{
        viewModelScope.launch {
            val words = repository.getWordsFromCategoryByName(nameOfCategory = category)
            words[(words.indices).random()]
        }
    }

    fun getSpy(players : Int) =
        (1..players).random()

    fun changeState(){
        if (cardState.value is CardState.ClosedCard){
            cardState.value = CardState.OpenedCard((cardState.value as CardState).number + 1)
        } else {
            cardState.value = CardState.ClosedCard((cardState.value as CardState).number)
        }
    }
}

sealed class CardState(val number : Int){
    class ClosedCard(number : Int) : CardState(number)
    class OpenedCard(number : Int) : CardState(number)
}