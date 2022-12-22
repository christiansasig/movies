package com.christiansasig.movies.movie.viewmodel

import androidx.lifecycle.viewModelScope
import com.christiansasig.movies.core.navigation.Screens
import com.christiansasig.movies.core.navigation.compose.NavigatorParams
import com.christiansasig.movies.core.viewmodel.BaseViewModel
import com.christiansasig.movies.domain.tv.GetPopularTvUseCase
import com.christiansasig.movies.domain.viewstate.extension.*
import com.christiansasig.movies.movie.uistate.TvShowUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
 private val getPopularTvUseCase: GetPopularTvUseCase
) : BaseViewModel() {

    var tvShowUiState = MutableStateFlow(TvShowUiState.Empty)
        private set


    fun getPopularTv() {
        viewModelScope.launch {
            tvShowUiState.value = TvShowUiState(
                isLoading = true, isVisible = true
            )
            getPopularTvUseCase() collectAsSuccess {
                tvShowUiState.value = TvShowUiState(
                    isLoading = false, data = it , isVisible = it != null
                )
            } collectAsUnAuthorized {
                setEffect { NavigatorParams(screen = Screens.LoginScreen) }
            } collectAsUnAuthorized {
                tvShowUiState.value = TvShowUiState(
                    isLoading = false
                )
                setEffect { NavigatorParams(screen = Screens.LoginScreen) }
            } collectAsFailureErrorModel {
                tvShowUiState.value = TvShowUiState(
                    isLoading = false,
                    isError = true
                )
            } collectAsFailure {
                tvShowUiState.value = TvShowUiState(
                    isLoading = false,
                    isError = true
                )
            } collectAsErrorConnection {
                tvShowUiState.value = TvShowUiState(
                    isLoading = false,
                    isErrorConnection = true
                )
            }
        }
    }
}