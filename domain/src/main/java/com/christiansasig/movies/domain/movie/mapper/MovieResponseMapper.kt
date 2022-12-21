package com.christiansasig.movies.domain.movie.mapper

import com.christiansasig.movies.moviedata.model.MoviePopularResult
import com.christiansasig.movies.moviedata.model.MovieResult
import com.christiansasig.movies.network.model.movie.popular.MoviePopularResponse
import com.christiansasig.movies.network.model.movie.popular.MovieResponse

internal fun MoviePopularResponse.toMoviePopularResult() =
    MoviePopularResult(
        page = page,
        results = results?.map { it.toMovieResponseResult() },
        totalResults = totalResults,
        totalPages = totalPages
    )

internal fun MovieResponse.toMovieResponseResult() =
    MovieResult(
        id = id,
        title = title,
        posterPath = posterPath,
        voteAverage = voteAverage,
        backdropPath = backdropPath,
        overview = overview,
        releaseDate = releaseDate,
        originalTitle = originalTitle,
        popularity = popularity
    )