package com.christiansasig.movies.moviedata.model

data class MovieResult(
    val id: Long,
    val title: String?,
    val posterPath: String?,
    val voteAverage: Float?,
    val backdropPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val originalTitle: String?,
    val popularity: Float?
)