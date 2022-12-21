package com.christiansasig.movies.core.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.christiansasig.movies.core.navigation.compose.ComposeAppNavigator.LAUNCH_LISTEN_FOR_EFFECTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigatorEffectFlow(
    effectFlow: Flow<NavigatorEffectFlowParams>,
    onNavigateTo: (NavigatorEffectFlowParams) -> Unit
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow
            .onEach { effect ->
                onNavigateTo(effect)
            }
            .collect()
    }
}

data class NavigatorEffectFlowParams(
    val screen: String,
    val params: Any? = null
)

@Composable
fun NavigatorEffect(
    effectFlow: Flow<NavigatorParams>,
    onNavigateTo: (NavigatorParams) -> Unit
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow
            .onEach { effect ->
                onNavigateTo(effect)
            }
            .collect()
    }
}