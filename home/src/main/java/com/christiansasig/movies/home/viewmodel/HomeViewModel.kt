package com.christiansasig.movies.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.christiansasig.movies.core.viewmodel.BaseViewModel
import com.christiansasig.movies.home.uistate.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel() {

    var homeUiState = MutableStateFlow(HomeUiState.Empty)
        private set


    fun getHomeInfo() {
        viewModelScope.launch {

        }
    }

}