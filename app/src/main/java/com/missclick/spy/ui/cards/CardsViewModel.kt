package com.missclick.spy.ui.cards

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.missclick.spy.data.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CardsViewModel(private val repository: IRepository) : ViewModel() {

    val cardState = MutableLiveData<CardState>(CardState.ClosedCard(1))


    fun getRandomWord(category : String) = liveData<String>(Dispatchers.IO){
        try {
            val words = repository.getWordsFromCategoryByName(nameOfCategory = category)
            emit(words[(words.indices).random()])
        } catch (ex : Exception){
            ex.printStackTrace()
        }

    }

    fun getSpy(players : Int) =
        (1..players).random()

    fun changeState(players: Int){
        if (cardState.value is CardState.ClosedCard){
            cardState.value = CardState.OpenedCard((cardState.value as CardState).number + 1)
        } else {
            val number = (cardState.value as CardState).number
            if(number > players) cardState.value = CardState.EndCard
            cardState.value = CardState.ClosedCard(number)
        }
    }
}

sealed class CardState(val number : Int){
    class ClosedCard(number : Int) : CardState(number)
    class OpenedCard(number : Int) : CardState(number)
    object EndCard : CardState(0)
}