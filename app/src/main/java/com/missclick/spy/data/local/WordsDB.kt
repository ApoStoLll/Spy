package com.missclick.spy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.missclick.spy.data.local.entities.WordEntities

@Database(entities = [WordEntities::class], version = 1)
abstract class WordsDB : RoomDatabase(){
    abstract fun dao() : WordsDao
}