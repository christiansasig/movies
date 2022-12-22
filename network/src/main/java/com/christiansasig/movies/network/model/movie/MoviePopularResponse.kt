package com.christiansasig.movies.network.model.movie.popular

import com.google.gson.annotations.SerializedName

data class MoviePopularResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieResponse>?,
    @SerializedName("total_results") val totalResults: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
)