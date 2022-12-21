package com.christiansasig.movies.home.uistate

data class HomeUiState(
    val isLoading: Boolean = true,
    val isVisible: Boolean = true,
    val isError: Boolean = false,
    val isErrorConnection: Boolean = false,
    //val homeResult: HomeResult? = null,
) {
    companion object {
        val Empty = HomeUiState()
    }
}
