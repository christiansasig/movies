package com.christiansasig.movies.domain.tv.mapper

import com.chistiansasig.movies.tvshowdata.model.TvPopularResult
import com.chistiansasig.movies.tvshowdata.model.TvResult
import com.christiansasig.movies.network.model.tvshow.TvPopularResponse
import com.christiansasig.movies.network.model.tvshow.TvResponse

internal fun TvPopularResponse.toTvPopularResult() =
    TvPopularResult(
        page = page,
        results = results?.map { it.toTvResult() },
        totalResults = totalResults,
        totalPages = totalPages
    )

internal fun TvResponse.toTvResult() =
    TvResult(
        id = id,
        name = name,
        posterPath = posterPath,
        voteAverage = voteAverage,
        backdropPath = backdropPath,
        overview = overview,
        releaseDate = releaseDate,
        originalName = originalName,
        popularity = popularity
    )