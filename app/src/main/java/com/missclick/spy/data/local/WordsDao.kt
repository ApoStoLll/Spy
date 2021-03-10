package com.missclick.spy.data.local

import androidx.room.*
import com.missclick.spy.data.local.entities.WordEntitity

@Dao
interface WordsDao{
    @Query("SELECT * FROM db")
    fun get() : List<WordEntitity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: WordEntitity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(words : List<WordEntitity>)

    @Delete
    fun remove(word: WordEntitity)

    @Query("SELECT * FROM db WHERE id = :id")
    fun getId(id : Int) : WordEntitity

    @Query("SELECT * FROM db WHERE category = :category")
    fun getWordsByCategory(category : String) : List<WordEntitity>
}