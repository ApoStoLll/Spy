package com.missclick.spy.data

import com.missclick.spy.data.local.ILocalDataSource

class Repository(private val localDataSource: ILocalDataSource) : IRepository {
    override suspend fun getWordsFromCategoryByName(nameOfCategory: String): List<String> {
        return listOf("Peace","Door","Ball")
    }


}