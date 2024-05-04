package com.example.mvvm_learning.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvm_learning.models.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<Result>

    @Query("DELETE  FROM quote")
    suspend fun deleteQuotes()
}