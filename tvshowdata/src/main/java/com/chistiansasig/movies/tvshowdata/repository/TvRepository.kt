package com.chistiansasig.movies.tvshowdata.repository

import com.christiansasig.movies.network.di.util.ErrorType
import com.christiansasig.movies.network.di.util.ResultType
import com.christiansasig.movies.network.model.tvshow.TvPopularResponse
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    suspend fun getPopularTv()
            : Flow<ResultType<TvPopularResponse?, ErrorType>>
}