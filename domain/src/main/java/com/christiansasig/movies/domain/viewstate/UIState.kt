package com.christiansasig.movies.domain.viewstate

import com.christiansasig.movies.network.model.error.ErrorModel


sealed class UIState<out T> where T : Any? {
    object Loading : UIState<Nothing>()
    object Unknown : UIState<Nothing>()
    object UnAuthorized : UIState<Nothing>()
    object UnExpectedError : UIState<Nothing>()
    object EmptySuccess : UIState<Nothing>()
    object ConnectionError : UIState<Nothing>()

    data class Success<T>(
        val data: T
    ) : UIState<T>()

    data class Failure<T>(
        val errorMessage: T
    ) : UIState<Nothing>()

    data class FailureCodeException<T>(
        val exception: T
    ) : UIState<Nothing>()

    data class FailureErrorModel(
        val errorMessage: ErrorModel
    ) : UIState<Nothing>()
}

