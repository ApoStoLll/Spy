package com.missclick.spy.data.local

import com.missclick.spy.data.local.ILocalDataSource
import com.missclick.spy.data.local.WordsDB
import com.missclick.spy.data.local.entities.WordEntitity

class LocalDataSource(private val wordsDB: WordsDB) : ILocalDataSource{
    override suspend fun addWord(word : WordEntitity) =
        wordsDB.dao().insert(word)

    override suspend fun getWords(category : String): List<WordEntitity> {
        return wordsDB.dao().getWordsByCategory(category = category)
    }

}