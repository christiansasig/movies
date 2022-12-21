package com.christiansasig.movies.network.model

import com.google.gson.annotations.SerializedName

data class Data<T>(
    @SerializedName("data")
    val data: T
)