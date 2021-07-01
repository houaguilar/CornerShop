package com.example.cornershop.di

import com.example.cornershop.ui.main.MainCounterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun presenterModule() = module {
    viewModel { MainCounterViewModel(get()) }

}