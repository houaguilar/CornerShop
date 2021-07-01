package com.example.cornershop.data.database

import androidx.room.*
import com.example.cornershop.data.model.Counter

@Dao
interface CounterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCounter(counters: List<Counter>)

    @Query("SELECT * FROM counter")
    suspend fun getSavedCounters(): List<Counter>

//    @Delete
//    suspend fun deleteCounter()

//    @Query("SELECT * FROM counter WHERE id = :id")
//    suspend fun(id: String):
}