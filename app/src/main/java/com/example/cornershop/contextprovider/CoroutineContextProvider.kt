package com.example.cornershop.contextprovider

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {

    fun context(): CoroutineContext
}