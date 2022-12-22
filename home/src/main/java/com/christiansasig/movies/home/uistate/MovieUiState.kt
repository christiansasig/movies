package com.christiansasig.movies.home.uistate

import com.christiansasig.movies.moviedata.model.MoviePopularResult

data class MovieUiState(
    val isLoading: Boolean = true,
    val isVisible: Boolean = true,
    val isError: Boolean = false,
    val isErrorConnection: Boolean = false,
    val data: MoviePopularResult? = null,
) {
    companion object {
        val Empty = MovieUiState()
    }
}
