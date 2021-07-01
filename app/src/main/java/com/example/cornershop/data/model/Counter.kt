package com.example.cornershop.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "counter")
data class Counter(
    @PrimaryKey
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("count")
    val count: Int
)

data class CreateNewCounter(
        val title: String
)

data class IdCounter(
        val id: String
)