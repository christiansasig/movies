package com.christiansasig.movies.home.uistate

import com.chistiansasig.movies.tvshowdata.model.TvPopularResult

data class TvUiState(
    val isLoading: Boolean = true,
    val isVisible: Boolean = true,
    val isError: Boolean = false,
    val isErrorConnection: Boolean = false,
    val data: TvPopularResult? = null,
) {
    companion object {
        val Empty = TvUiState()
    }
}
