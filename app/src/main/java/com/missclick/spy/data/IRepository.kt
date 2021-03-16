package com.missclick.spy.data

import com.missclick.spy.data.models.WordsModel

interface IRepository {
    suspend fun getWordsFromCategoryByName(nameOfCategory : String) : List<String>
    suspend fun insertWord(wordsModel : WordsModel) : Long
    suspend fun getSets() : List<String>
}