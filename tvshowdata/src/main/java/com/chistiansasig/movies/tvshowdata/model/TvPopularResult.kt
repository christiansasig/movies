package com.chistiansasig.movies.tvshowdata.model

data class TvPopularResult(
    val page: Int?,
    val results: List<TvResult>?,
    val totalResults: Int?,
    val totalPages: Int?,
)