package com.example.cornershop.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cornershop.data.model.Counter

@Database(entities = [Counter::class], version = 1)
abstract class CounterDatabase: RoomDatabase() {

    abstract fun counterDao(): CounterDao

    companion object{
        fun create(context: Context): CounterDatabase{

            return Room.databaseBuilder(
                context,
                CounterDatabase::class.java,
                "counters"
            ).build()
        }
    }

}