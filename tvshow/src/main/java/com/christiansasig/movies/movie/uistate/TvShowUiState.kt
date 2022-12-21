package com.christiansasig.movies.movie.uistate

data class TvShowUiState(
    val isLoading: Boolean = true,
    val isVisible: Boolean = true,
    val isError: Boolean = false,
    val isErrorConnection: Boolean = false,
    //val data: List<ProposalResult> = emptyList(),
) {
    companion object {
        val Empty = TvShowUiState()
    }
}
