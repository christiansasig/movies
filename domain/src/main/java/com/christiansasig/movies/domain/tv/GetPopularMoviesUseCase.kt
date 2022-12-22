package com.christiansasig.movies.domain.tv

import com.chistiansasig.movies.tvshowdata.datasource.TvDataSource
import com.chistiansasig.movies.tvshowdata.model.TvPopularResult
import com.christiansasig.movies.domain.tv.mapper.toTvPopularResult
import com.christiansasig.movies.domain.usecase.EmptyParameterFlowUseCase
import com.christiansasig.movies.domain.viewstate.UIState
import com.christiansasig.movies.network.di.util.ErrorType
import com.christiansasig.movies.network.di.util.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularTvUseCase @Inject constructor(
    private val tvDataSource: TvDataSource,
) : EmptyParameterFlowUseCase<TvPopularResult?>() {
    override suspend fun execute()
            : Flow<UIState<TvPopularResult?>> =
        tvDataSource.getPopularTv()
            .map { result ->
                if (result is ResultType.Success) {
                    return@map UIState.Success(
                        result.value?.toTvPopularResult()
                    )
                }

                if (result is ResultType.Error) {
                    val error = result.value
                    if (error is ErrorType.Error) {
                        return@map UIState.FailureErrorModel(error.errorModel)
                    }
                    if (error is ErrorType.Exception) {
                        return@map UIState.Failure(error)
                    }
                    if (error is ErrorType.NetworkConnectionHttpException) {
                        return@map UIState.ConnectionError
                    }
                    if (error is ErrorType.NetworkAuthHttpException) {
                        return@map UIState.UnAuthorized
                    }
                }

                UIState.UnExpectedError
            }
}