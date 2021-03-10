package com.missclick.spy.data

import com.missclick.spy.data.models.WordModel

interface IRepository {
    suspend fun getWordsFromCategoryByName(nameOfCategory : String) : List<String>
    suspend fun insertWord(wordModel : WordModel) : Long
}