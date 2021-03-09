package com.missclick.spy.data.local

import com.missclick.spy.data.local.ILocalDataSource
import com.missclick.spy.data.local.WordsDB

class LocalDataSource(private val wordsDB: WordsDB) : ILocalDataSource{
    override suspend fun addWord() {
        TODO("Not yet implemented")
    }

}