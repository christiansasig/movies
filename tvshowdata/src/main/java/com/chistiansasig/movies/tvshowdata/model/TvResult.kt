package com.chistiansasig.movies.tvshowdata.model

data class TvResult(
    val id: Long,
    val name: String?,
    val posterPath: String?,
    val voteAverage: Float?,
    val backdropPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val originalName: String?,
    val popularity: Float?
)