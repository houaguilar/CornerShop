package com.example.cornershop.di

import com.example.cornershop.contextprovider.CoroutineContextProvider
import com.example.cornershop.contextprovider.CoroutineContextProviderImpl
import com.example.cornershop.data.database.CounterDatabase
import com.example.cornershop.domain.CounterRepository
import com.example.cornershop.domain.CounterRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun appModule() = module {

    single { CoroutineContextProviderImpl(Dispatchers.IO) as CoroutineContextProvider }

    single { CounterDatabase.create(androidContext()) }

    single { get<CounterDatabase>().counterDao() }

    single { CounterRepositoryImpl(get(), get()) as CounterRepository }
}