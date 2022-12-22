package com.christiansasig.movies.network.model.tvshow

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val voteAverage: Float?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("popularity") val popularity: Float?
)