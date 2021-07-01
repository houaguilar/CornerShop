package com.example.cornershop.utils

import android.util.Log
import kotlin.coroutines.CoroutineContext

fun logCoroutine(methodName: String, coroutineContext: CoroutineContext) {
    Log.d(
        "TestCoroutine", "Thread for $methodName is: ${Thread.currentThread().name}" +
                "and the context is $coroutineContext"
    )
}