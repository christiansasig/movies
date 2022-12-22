package com.christiansasig.movies.network.di.webservice

import com.christiansasig.movies.network.model.movie.MoviePopularResponse
import com.christiansasig.movies.network.model.tvshow.TvPopularResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebServiceApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviePopularResponse>

    @GET("tv/popular")
    suspend fun getPopularTv(): Response<TvPopularResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(): Response<MoviePopularResponse>


}