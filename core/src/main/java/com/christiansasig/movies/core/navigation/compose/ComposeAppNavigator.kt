package com.christiansasig.movies.core.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.christiansasig.movies.core.navigation.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

// expose class to viewModel
@Suppress("ObjectPropertyName")
object ComposeAppNavigator {
    const val LAUNCH_LISTEN_FOR_EFFECTS = "compose_navigation_channel"

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private val _effect: Channel<NavigatorParams> = Channel()
    val effectNavigator = _effect.receiveAsFlow()

    fun setNavigatorEffect(builder: () -> NavigatorParams) {
        val effectValue = builder()
        scope.launch {
            _effect.trySend(effectValue)
        }
    }
}

// expose class to composable screens
@Composable
fun rememberAppNavigator(): ComposeAppNavigator {
    return remember { ComposeAppNavigator }
}

data class NavigatorParams(
    val screen: Screens,
    val params: Any? = null
)