package com.christiansasig.movies.moviedata.repository

import com.christiansasig.movies.network.di.util.ErrorType
import com.christiansasig.movies.network.di.util.ResultType
import com.christiansasig.movies.network.model.movie.popular.MoviePopularResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies()
            : Flow<ResultType<MoviePopularResponse?, ErrorType>>
}