package com.example.cornershop.data.api

import com.example.cornershop.data.model.Counter
import com.example.cornershop.data.model.CreateNewCounter
import com.example.cornershop.data.model.IdCounter
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface CounterApiService {

    @GET("counters")
    suspend fun getListCounters(): ArrayList<Counter>

    @POST("counter")
    suspend fun postCounter(@Body createNewCounter: CreateNewCounter): ArrayList<Counter>

    @POST("counter/inc")
    suspend fun postIncCounter(@Body idCounter: IdCounter): ArrayList<Counter>

    @POST("counter/dec")
    suspend fun postDecCounter(@Body idCounter: IdCounter): ArrayList<Counter>

    //    @DELETE("counter")
//    suspend fun deleteCounter(@Body idCounter: IdCounter): ArrayList<Counter>
    @HTTP(method = "DELETE", path = "counter", hasBody = true)
    suspend fun deleteCounter(@Body idCounter: IdCounter): ArrayList<Counter>
}