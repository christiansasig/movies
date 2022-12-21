package com.christiansasig.movies.domain.movie

import com.christiansasig.movies.domain.movie.mapper.toMoviePopularResult
import com.christiansasig.movies.domain.usecase.EmptyParameterFlowUseCase
import com.christiansasig.movies.domain.viewstate.UIState
import com.christiansasig.movies.moviedata.datasource.MovieDataSource
import com.christiansasig.movies.moviedata.model.MoviePopularResult
import com.christiansasig.movies.network.di.util.ErrorType
import com.christiansasig.movies.network.di.util.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieDataSource: MovieDataSource,
) : EmptyParameterFlowUseCase<MoviePopularResult?>() {
    override suspend fun execute()
            : Flow<UIState<MoviePopularResult?>> =
        movieDataSource.getPopularMovies()
            .map { result ->
                if (result is ResultType.Success) {
                    return@map UIState.Success(
                        result.value?.toMoviePopularResult()
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