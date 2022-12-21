package com.christiansasig.movies.domain.usecase

import com.christiansasig.movies.domain.viewstate.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R> {
    suspend operator fun invoke(parameters: P): Flow<UIState<R>> =
        execute(parameters)
            .flowOn(Dispatchers.Main)
            .catch { e -> emit(UIState.Failure(Exception(e))) }

    protected abstract suspend fun execute(parameters: P): Flow<UIState<R>>
}

abstract class ValueUseCase<in P, R> {
    suspend operator fun invoke(parameters: P): UIState<R> =
        kotlin.runCatching { execute(parameters) }
            .getOrElse { UIState.Failure(Exception(it)) }

    protected abstract suspend fun execute(parameters: P): UIState<R>
}

abstract class EmptyValueUseCase<R> {
    suspend operator fun invoke(): UIState<R> =
        kotlin.runCatching { execute() }
            .getOrElse { UIState.Failure(Exception(it)) }

    protected abstract suspend fun execute(): UIState<R>
}

abstract class EmptyParameterFlowUseCase<R> {
    suspend operator fun invoke(): Flow<UIState<R>> =
        execute()
            .catch { e -> emit(UIState.Failure(Exception(e))) }

    protected abstract suspend fun execute(): Flow<UIState<R>>
}
