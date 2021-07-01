package com.example.cornershop.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.cornershop.data.Resource
import com.example.cornershop.domain.CounterRepository
import com.example.cornershop.utils.logCoroutine
import kotlinx.coroutines.*
import java.lang.Exception

class MainCounterViewModel(private val counterRepository: CounterRepository) : ViewModel(), MainCountersPresenter {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        throwable.printStackTrace()
    }

    private lateinit var countersView: MainCountersView

    override fun setView(mainCountersView: MainCountersView) {
        this.countersView = mainCountersView
    }

    override fun getData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            logCoroutine("getData", coroutineContext)

            delay(500)
            val result = kotlin.runCatching { counterRepository.getCounters() }

            Log.d("TestCoroutine", "Still Alive!")
            result.onSuccess { counters ->
                countersView.showCounters(counters)
            }.onFailure { error ->
                handleError(error)
            }
        }
    }

    override fun createCounter(title: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(counterRepository.postNewCounter(title)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    override fun incCounter(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(counterRepository.postIncCounter(id)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    override fun decCounter(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(counterRepository.postDecCounter(id)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    override fun deleteCounter(id: String) {
        viewModelScope.launch {
            counterRepository.deleteCounter(id)
        }
    }

    private fun handleError(throwable: Throwable) {
        countersView.showError(throwable)
    }
}