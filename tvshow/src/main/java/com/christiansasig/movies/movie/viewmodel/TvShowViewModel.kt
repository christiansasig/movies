package com.christiansasig.movies.movie.viewmodel

import androidx.lifecycle.viewModelScope
import com.christiansasig.movies.core.viewmodel.BaseViewModel
import com.christiansasig.movies.movie.uistate.TvShowUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(

) : BaseViewModel() {

    var proposalUiState = MutableStateFlow(TvShowUiState.Empty)
        private set


    fun getProposalInfo() {
        viewModelScope.launch {
        }
    }
}