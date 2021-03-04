package com.missclick.spy.di

import android.content.Context
import androidx.room.Room
import com.missclick.spy.data.IRepository
import com.missclick.spy.data.Repository
import com.missclick.spy.data.local.ILocalDataSource
import com.missclick.spy.data.local.WordsDB
import com.missclick.spy.data.local.WordsDao
import com.missclick.spy.data.local.entities.LocalDataSource
import org.koin.dsl.module

val dataModule = module {
    single {
        provideDb(get())
    }
    single {
        provideLocalDataSource(get())
    }
    single {
        provideRepository(get())
    }
}

fun provideDb(context: Context) : WordsDB {
    return Room.databaseBuilder(context, WordsDB::class.java, "db")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideLocalDataSource(wordsDB: WordsDB) : ILocalDataSource{
    return LocalDataSource(wordsDB)
}

fun provideRepository(localDataSource: ILocalDataSource): IRepository {
    return Repository(localDataSource)
}