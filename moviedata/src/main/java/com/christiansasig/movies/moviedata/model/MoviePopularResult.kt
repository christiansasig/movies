package com.christiansasig.movies.moviedata.model

data class MoviePopularResult(
    val page: Int?,
    val results: List<MovieResult>?,
    val totalResults: Int?,
    val totalPages: Int?,
)