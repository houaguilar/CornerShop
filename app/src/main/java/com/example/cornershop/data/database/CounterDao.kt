package com.example.cornershop.data.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cornershop.data.model.Counter

interface CounterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCounter(stories: List<Counter>)

    @Query("SELECT * FROM counter")
    suspend fun getSavedCounters(): List<Counter>

//    @Query("SELECT * FROM counter WHERE id = :id")
//    suspend fun(id: String):
}