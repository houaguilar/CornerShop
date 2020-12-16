package com.example.cornershop.data.api

import androidx.lifecycle.LiveData
import com.example.cornershop.data.model.CounterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CounterApiService {

    @GET("counters")
    suspend fun getCounters(@Path("id") id:String): CounterResponse?

//    @GET("users/{login}")
//    fun getUser(@Path("login") login: String): LiveData<ApiResponse<User>>
}