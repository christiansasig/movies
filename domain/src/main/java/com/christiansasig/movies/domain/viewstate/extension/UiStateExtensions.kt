package com.christiansasig.movies.domain.viewstate.extension

import com.christiansasig.movies.domain.viewstate.UIState
import com.christiansasig.movies.network.model.error.ErrorModel
import kotlinx.coroutines.flow.Flow

suspend inline infix fun <T> Flow<UIState<T>>.collectAsSuccess(
    crossinline action: suspend (value: T) -> Unit
): Flow<UIState<T>> {
    collect {
        if (it is UIState.Success) {
            action(it.data)
        }
    }
    return this
}

suspend inline infix fun <T> Flow<UIState<T>>.collectAsSuccessForWorker(
    crossinline action: suspend (value: UIState<T>) -> Unit
): Flow<UIState<T>> {
    collect {
        if (it is UIState.Success) {
            action(it)
        }
    }
    return this
}

suspend inline infix fun <T> UIState<T>.collectAsSuccess(
    crossinline action: suspend (value: T) -> Unit
): UIState<T> {
    if (this is UIState.Success) {
        action(this.data)
    }
    return this
}

suspend inline infix fun <T> Flow<UIState<T>>.collectAsFailureErrorModel(
    crossinline action: suspend (value: ErrorModel) -> Unit
): Flow<UIState<T>> {
    collect {
        if (it is UIState.FailureErrorModel) {
            action(it.errorMessage)
        }
    }
    return this
}

suspend inline infix fun <T> UIState<T>.collectAsFailureErrorModel(
    crossinline action: suspend (value: ErrorModel) -> Unit
): UIState<T> {
    if (this is UIState.FailureErrorModel) {
        action(this.errorMessage)
    }
    return this
}

suspend inline infix fun <T> Flow<UIState<T>>.collectAsFailure(
    crossinline action: suspend (value: UIState.UnExpectedError) -> Unit
): Flow<UIState<T>> {
    collect {
        if (it is UIState.Failure<*>) {
            action(UIState.UnExpectedError)
        }
    }
    return this
}

suspend inline infix fun <T> UIState<T>.collectAsFailure(
    crossinline action: suspend (value: UIState.UnExpectedError) -> Unit
): UIState<T> {
    if (this is UIState.Failure<*>) {
        action(UIState.UnExpectedError)
    }
    return this
}

suspend inline infix fun <T> Flow<UIState<T>>.collectErrorCodeException(
    crossinline action: suspend (value: Any) -> Unit
): Flow<UIState<T>> {
    collect {
        if (it is UIState.FailureCodeException<*>) {
            action(it)
        }
    }
    return this
}

suspend inline infix fun <T> Flow<UIState<T>>.collectAsUnAuthorized(
    crossinline action: suspend (value: UIState.UnAuthorized) -> Unit
): Flow<UIState<T>> {
    collect {
        if (it is UIState.UnAuthorized) {
            action(UIState.UnAuthorized)
        }
    }
    return this
}

suspend inline infix fun <T> Flow<UIState<T>>.collectAsErrorConnection(
    crossinline action: suspend (value: UIState.ConnectionError) -> Unit
): Flow<UIState<T>> {
    collect {
        if (it is UIState.ConnectionError) {
            action(it)
        }
    }
    return this
}

suspend inline infix fun <T> UIState<T>.collectAsErrorConnection(
    crossinline action: suspend (value: UIState.ConnectionError) -> Unit
): UIState<T> {
    if (this is UIState.ConnectionError) {
        action(this)
    }
    return this
}