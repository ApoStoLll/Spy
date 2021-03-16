package com.missclick.spy.data

import android.util.Log
import com.missclick.spy.data.local.ILocalDataSource
import com.missclick.spy.data.local.entities.WordEntitity
import com.missclick.spy.data.models.WordModel

class Repository(private val localDataSource: ILocalDataSource) : IRepository {
    override suspend fun getWordsFromCategoryByName(nameOfCategory: String): List<String> =
        localDataSource.getWords(nameOfCategory).map {
            it.word
        }
        //return listOf("Peace","Door","Ball")


    override suspend fun insertWord(wordModel: WordModel): Long {

        val id =  localDataSource.addWord(
            WordEntitity(
                word = wordModel.word,
                category = wordModel.category
            )
        )
        Log.e("Repository", id.toString())
        return id
    }

    override suspend fun getSets(): List<String> {
        return localDataSource.getSets()
    }


}