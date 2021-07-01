package com.example.cornershop.domain

import com.example.cornershop.data.model.Counter

interface CounterRepository {

    suspend fun getCounters(): ArrayList<Counter>?

    suspend fun postNewCounter(title: String): ArrayList<Counter>

    suspend fun postIncCounter(id: String): ArrayList<Counter>

    suspend fun postDecCounter(id: String): ArrayList<Counter>

    suspend fun deleteCounter(id: String): ArrayList<Counter>?

}