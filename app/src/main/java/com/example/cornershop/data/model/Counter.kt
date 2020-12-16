package com.example.cornershop.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class Counter(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("count")
    val count: Int
)