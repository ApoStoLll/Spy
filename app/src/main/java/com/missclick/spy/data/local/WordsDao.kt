package com.missclick.spy.data.local

import androidx.room.*
import com.missclick.spy.data.local.entities.WordEntities

@Dao
interface WordsDao{
    @Query("SELECT * FROM db")
    fun get() : List<WordEntities>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: WordEntities)

    @Delete
    fun remove(word: WordEntities)

    @Query("SELECT * FROM db WHERE id = :id")
    fun getId(id : Int) : WordEntities
}