package com.chistiansasig.movies.viewmodel

import androidx.lifecycle.viewModelScope
import com.christiansasig.movies.core.navigation.Screens
import com.christiansasig.movies.core.navigation.compose.NavigatorEffectFlowParams
import com.christiansasig.movies.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel() {

    fun openLogin() {
        viewModelScope.launch {
            setEffectFlow { NavigatorEffectFlowParams(screen = Screens.DashboardScreen.route) }
        }
    }
}