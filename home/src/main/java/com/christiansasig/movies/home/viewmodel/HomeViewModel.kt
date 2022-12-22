package com.christiansasig.movies.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.christiansasig.movies.core.navigation.Screens
import com.christiansasig.movies.core.navigation.compose.NavigatorParams
import com.christiansasig.movies.core.viewmodel.BaseViewModel
import com.christiansasig.movies.domain.movie.GetNowPlayingUseCase
import com.christiansasig.movies.domain.movie.GetPopularMoviesUseCase
import com.christiansasig.movies.domain.tv.GetPopularTvUseCase
import com.christiansasig.movies.domain.viewstate.extension.*
import com.christiansasig.movies.home.uistate.MovieNowPlayingUiState
import com.christiansasig.movies.home.uistate.MovieUiState
import com.christiansasig.movies.home.uistate.TvUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularTvUseCase: GetPopularTvUseCase,
    private val getNowPlayingUseCase: GetNowPlayingUseCase
) : BaseViewModel() {

    var movieUiState = MutableStateFlow(MovieUiState.Empty)
        private set

    var tvUiState = MutableStateFlow(TvUiState.Empty)
        private set

    var movieNowPlayingUiState = MutableStateFlow(MovieNowPlayingUiState.Empty)
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

    fun getPopularTv() {
        viewModelScope.launch {
            tvUiState.value = TvUiState(
                isLoading = true, isVisible = true
            )
            getPopularTvUseCase() collectAsSuccess {
                tvUiState.value = TvUiState(
                    isLoading = false, data = it, isVisible = it != null
                )
            } collectAsUnAuthorized {
                setEffect { NavigatorParams(screen = Screens.LoginScreen) }
            } collectAsUnAuthorized {
                tvUiState.value = TvUiState(
                    isLoading = false
                )
                setEffect { NavigatorParams(screen = Screens.LoginScreen) }
            } collectAsFailureErrorModel {
                tvUiState.value = TvUiState(
                    isLoading = false,
                    isError = true
                )
            } collectAsFailure {
                tvUiState.value = TvUiState(
                    isLoading = false,
                    isError = true
                )
            } collectAsErrorConnection {
                tvUiState.value = TvUiState(
                    isLoading = false,
                    isErrorConnection = true
                )
            }
        }
    }

    fun getMoviesNowPlaying() {
        viewModelScope.launch {
            movieNowPlayingUiState.value = MovieNowPlayingUiState(
                isLoading = true, isVisible = true
            )
            getNowPlayingUseCase() collectAsSuccess {
                movieNowPlayingUiState.value = MovieNowPlayingUiState(
                    isLoading = false, data = it, isVisible = it != null
                )
            } collectAsUnAuthorized {
                setEffect { NavigatorParams(screen = Screens.LoginScreen) }
            } collectAsUnAuthorized {
                movieNowPlayingUiState.value = MovieNowPlayingUiState(

                    isLoading = false
                )
                setEffect { NavigatorParams(screen = Screens.LoginScreen) }
            } collectAsFailureErrorModel {
                movieNowPlayingUiState.value = MovieNowPlayingUiState(
                    isLoading = false,
                    isError = true
                )
            } collectAsFailure {
                movieNowPlayingUiState.value = MovieNowPlayingUiState(
                    isLoading = false,
                    isError = true
                )
            } collectAsErrorConnection {
                movieNowPlayingUiState.value = MovieNowPlayingUiState(
                    isLoading = false,
                    isErrorConnection = true
                )
            }
        }
    }
}