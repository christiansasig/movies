package com.christiansasig.movies.movie.viewmodel

import androidx.lifecycle.viewModelScope
import com.christiansasig.movies.core.navigation.Screens
import com.christiansasig.movies.core.navigation.compose.NavigatorParams
import com.christiansasig.movies.core.viewmodel.BaseViewModel
import com.christiansasig.movies.domain.movie.GetPopularMoviesUseCase
import com.christiansasig.movies.domain.viewstate.extension.*
import com.christiansasig.movies.movie.uistate.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel() {

    var movieUiState = MutableStateFlow(MovieUiState.Empty)
        private set


    fun getPopularMovies() {
        viewModelScope.launch {
            movieUiState.value = MovieUiState(
                isLoading = true, isVisible = true
            )
            getPopularMoviesUseCase() collectAsSuccess {
                movieUiState.value = MovieUiState(
                    isLoading = false, data = it, isVisible = it != null
                )
            } collectAsUnAuthorized {
                setEffect { NavigatorParams(screen = Screens.LoginScreen) }
            } collectAsUnAuthorized {
                movieUiState.value = MovieUiState(
                    isLoading = false
                )
                setEffect { NavigatorParams(screen = Screens.LoginScreen) }
            } collectAsFailureErrorModel {
                movieUiState.value = MovieUiState(
                    isLoading = false,
                    isError = true
                )
            } collectAsFailure {
                movieUiState.value = MovieUiState(
                    isLoading = false,
                    isError = true
                )
            } collectAsErrorConnection {
                movieUiState.value = MovieUiState(
                    isLoading = false,
                    isErrorConnection = true
                )
            }
        }
    }
}