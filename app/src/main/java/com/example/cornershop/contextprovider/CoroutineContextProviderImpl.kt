package com.example.cornershop.contextprovider

import kotlin.coroutines.CoroutineContext

class CoroutineContextProviderImpl(private val context: CoroutineContext): CoroutineContextProvider {

    override fun context(): CoroutineContext = context
}