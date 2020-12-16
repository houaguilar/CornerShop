package com.example.cornershop.domain

import com.example.cornershop.data.model.Counter

interface CounterRepository {

    suspend fun getCounters(): List<Counter>

}