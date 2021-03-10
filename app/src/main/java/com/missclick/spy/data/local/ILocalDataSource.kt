package com.missclick.spy.data.local

import com.missclick.spy.data.local.entities.WordEntitity

interface ILocalDataSource {
    suspend fun addWord(word : WordEntitity) : Long
    suspend fun getWords(category : String) : List<WordEntitity>
}