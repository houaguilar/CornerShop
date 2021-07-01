package com.example.cornershop.domain

import com.example.cornershop.data.api.CounterApiService
import com.example.cornershop.data.database.CounterDao
import com.example.cornershop.data.model.Counter
import com.example.cornershop.data.model.CreateNewCounter
import com.example.cornershop.data.model.IdCounter

class CounterRepositoryImpl(private val counterApiService: CounterApiService, private val counterDao: CounterDao): CounterRepository {

    override suspend fun getCounters(): ArrayList<Counter>? {
//        val cachedCounters = counterDao.getSavedCounters()

        val apiCounters = try {
            counterApiService.getListCounters()
        } catch (error: Throwable) {
            null
        }

//        if (apiCounters != null) {
//            counterDao.saveCounter(apiCounters)
//        }

//        return (apiCounters ?: cachedCounters) as ArrayList<Counter>
        return apiCounters
    }

    override suspend fun postNewCounter(title: String): ArrayList<Counter> {

        val create = try {
            counterApiService.postCounter(CreateNewCounter(title))
        } catch (error: Throwable) {
            null
        }

        return create!!
    }

    override suspend fun postIncCounter(id: String): ArrayList<Counter> {
        val inc = try {
            counterApiService.postIncCounter(IdCounter(id))
        } catch (error: Throwable) {
            null
        }

        return inc!!
    }

    override suspend fun postDecCounter(id: String): ArrayList<Counter> {

        val dec = try {
            counterApiService.postDecCounter(IdCounter(id))
        } catch (error: Throwable) {
            null
        }

        return dec!!
    }

    override suspend fun deleteCounter(id: String): ArrayList<Counter>? {

        val delete = try {
            counterApiService.deleteCounter(IdCounter(id))
        } catch (error: Throwable) {
            null
        }

        return delete
    }
}