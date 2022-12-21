package com.christiansasig.movies.network.model.error

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("errors")
    var errors: List<ApiError>?
) {

    fun getFirstErrorMessage(defaultMessage: String) =
        errors?.getOrNull(DEFAULT_ERROR_POSITION)?.message ?: defaultMessage

    companion object {
        const val DEFAULT_ERROR_POSITION = 0
    }
}