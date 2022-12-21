package com.christiansasig.movies.network.di.util

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import com.christiansasig.movies.network.model.error.ErrorResponse
import okhttp3.ResponseBody

/**
 * Convert retrofit's errorBody() to an error message.
 */
fun ResponseBody.toErrorMessage(defaultMessage: String): String =
    try {
        Gson().fromJson(this.charStream(), ErrorResponse::class.java)
            .getFirstErrorMessage(defaultMessage)
    } catch (ex: JsonIOException) {
        defaultMessage
    } catch (ex: JsonSyntaxException) {
        defaultMessage
    } catch (ex: NullPointerException) {
        defaultMessage
    }