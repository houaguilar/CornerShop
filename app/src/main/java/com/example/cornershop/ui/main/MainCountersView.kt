package com.example.cornershop.ui.main

import com.example.cornershop.data.model.Counter

interface MainCountersView {

    fun showCounters(counters: ArrayList<Counter>?)

    fun showError(throwable: Throwable)
}