package com.christiansasig.movies.moviedata.datasource

import com.christiansasig.movies.moviedata.repository.MovieRepository
import com.christiansasig.movies.network.di.DefaultDispatcher
import com.christiansasig.movies.network.di.util.ErrorType
import com.christiansasig.movies.network.di.util.ResultType
import com.christiansasig.movies.network.di.util.toErrorMessage
import com.christiansasig.movies.network.di.webservice.WebServiceApi
import com.christiansasig.movies.network.model.error.ErrorModel
import com.christiansasig.movies.network.model.error.ErrorModel.Companion.StatusCode401
import com.christiansasig.movies.network.model.error.ErrorModel.Companion.StatusCode422
import com.christiansasig.movies.network.model.error.ErrorModel.Companion.StatusCode500
import com.christiansasig.movies.network.model.movie.MoviePopularResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val api: WebServiceApi,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : MovieRepository {
    override suspend fun getPopularMovies(): Flow<ResultType<MoviePopularResponse?, ErrorType>> {
        val loanApplicationsResult = kotlin.runCatching {
            val queryResult = api.getPopularMovies()
            queryResult
        }

        return flow {
            loanApplicationsResult.onSuccess { data ->
                if (data.isSuccessful) {
                    emit(
                        ResultType.Success(
                            data.body()
                        )
                    )
                } else {
                    when (data.code()) {
                        StatusCode401 -> emit(
                            ResultType.Error(
                                ErrorType.NetworkAuthHttpException
                            )
                        )
                        StatusCode422 -> emit(
                            ResultType.Error(
                                ErrorType.Error(
                                    ErrorModel(
                                        code = data.code().toString(),
                                        message = data.errorBody()?.toErrorMessage("") ?: ""
                                    )
                                )
                            )
                        )
                        StatusCode500 -> emit(
                            ResultType.Error(
                                ErrorType.NetworkConnectionHttpException
                            )
                        )
                        else -> emit(
                            ResultType.Error(
                                ErrorType.Error(
                                    ErrorModel(
                                        code = data.code().toString(),
                                        message = data.message()
                                    )
                                )
                            )
                        )
                    }
                }

            }.onFailure { ex ->
                if (ex is SocketTimeoutException) {
                    emit(
                        ResultType.Error(
                            ErrorType.NetworkConnectionHttpException
                        )
                    )
                }

                if (ex is HttpException) {
                    emit(
                        ResultType.Error(
                            ErrorType.NetworkHttpExceptionWithCode(
                                retrofitHttpException = ex
                            )
                        )
                    )
                }
                emit(ResultType.Error(ErrorType.Exception(ex)))
            }

        }.flowOn(dispatcher)
    }

    override suspend fun getNowPlaying(): Flow<ResultType<MoviePopularResponse?, ErrorType>> {
        val loanApplicationsResult = kotlin.runCatching {
            val queryResult = api.getNowPlaying()
            queryResult
        }

        return flow {
            loanApplicationsResult.onSuccess { data ->
                if (data.isSuccessful) {
                    emit(
                        ResultType.Success(
                            data.body()
                        )
                    )
                } else {
                    when (data.code()) {
                        StatusCode401 -> emit(
                            ResultType.Error(
                                ErrorType.NetworkAuthHttpException
                            )
                        )
                        StatusCode422 -> emit(
                            ResultType.Error(
                                ErrorType.Error(
                                    ErrorModel(
                                        code = data.code().toString(),
                                        message = data.errorBody()?.toErrorMessage("") ?: ""
                                    )
                                )
                            )
                        )
                        StatusCode500 -> emit(
                            ResultType.Error(
                                ErrorType.NetworkConnectionHttpException
                            )
                        )
                        else -> emit(
                            ResultType.Error(
                                ErrorType.Error(
                                    ErrorModel(
                                        code = data.code().toString(),
                                        message = data.message()
                                    )
                                )
                            )
                        )
                    }
                }

            }.onFailure { ex ->
                if (ex is SocketTimeoutException) {
                    emit(
                        ResultType.Error(
                            ErrorType.NetworkConnectionHttpException
                        )
                    )
                }

                if (ex is HttpException) {
                    emit(
                        ResultType.Error(
                            ErrorType.NetworkHttpExceptionWithCode(
                                retrofitHttpException = ex
                            )
                        )
                    )
                }
                emit(ResultType.Error(ErrorType.Exception(ex)))
            }

        }.flowOn(dispatcher)
    }

}