package com.example.cornershop.ui.main

import androidx.lifecycle.LiveData
import com.example.cornershop.data.Resource
import com.example.cornershop.data.model.Counter

interface MainCountersPresenter {

    fun setView(mainCountersView: MainCountersView)

    fun getData()

    fun createCounter(title: String): LiveData<Resource<ArrayList<Counter>>>

    fun incCounter(id: String): LiveData<Resource<ArrayList<Counter>>>

    fun decCounter(id: String): LiveData<Resource<ArrayList<Counter>>>

    fun deleteCounter(id: String)
}