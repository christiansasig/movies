package com.christiansasig.movies.network.di.webservice

import com.christiansasig.movies.network.model.movie.popular.MoviePopularResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebServiceApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviePopularResponse>
}