package com.missclick.spy.data

interface IRepository {
    suspend fun getWordsFromCategoryByName(nameOfCategory : String) : List<String>
}