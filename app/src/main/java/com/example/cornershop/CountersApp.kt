package com.example.cornershop

import android.app.Application
import com.example.cornershop.di.appModule
import com.example.cornershop.di.networkingModule
import com.example.cornershop.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CountersApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CountersApp)
            modules(listOf(appModule(), networkingModule(), presenterModule()))
        }

        if (BuildConfig.DEBUG) System.setProperty("kotlinx.coroutines.debug", "on")
    }
}