package com.christiansasig.movies.network.model.error

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorModel(val code: String?, val title: String? = "", val message: String) : Parcelable{
    companion object {
        const val StatusCode201 = 201
        const val StatusCode401 = 401
        const val StatusCode422 = 422
        const val StatusCode500 = 500
    }
}