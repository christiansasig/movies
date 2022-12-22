package com.christiansasig.movies.network.model.tvshow

import com.google.gson.annotations.SerializedName

data class TvPopularResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<TvResponse>?,
    @SerializedName("total_results") val totalResults: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
)