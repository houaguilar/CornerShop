package com.example.cornershop.domain

import com.example.cornershop.data.api.CounterApiService
import com.example.cornershop.data.database.CounterDao
import com.example.cornershop.data.model.Counter

class CounterRepositoryImpl(private val counterApiService: CounterApiService, private val counterDao: CounterDao): CounterRepository {
    override suspend fun getCounters(): List<Counter> {
        val cachedCounters = counterDao.getSavedCounters()

        val apiCounters = try {
            counterApiService.getCounters("id")?.counters
        } catch (error: Throwable) {
            null
        }

        if (apiCounters != null) {
            counterDao.saveCounter(apiCounters)
        }

        return apiCounters ?: cachedCounters
    }
}