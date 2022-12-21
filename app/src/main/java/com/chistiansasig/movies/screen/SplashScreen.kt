package com.chistiansasig.movies.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chistiansasig.movies.navgraph.SplashNavigator
import com.chistiansasig.movies.viewmodel.SplashViewModel
import com.christiansasig.movies.core.R
import com.christiansasig.movies.core.navigation.Screens
import com.christiansasig.movies.core.navigation.compose.NavigatorEffectFlow
import com.christiansasig.movies.core.navigation.compose.SplashNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.delay

@SplashNavGraph(
    start = true
)
@Destination
@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigator: SplashNavigator
) {
    // remember the current scale
    val scale = remember { Animatable(0.5f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        viewModel.openLogin()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_movie),
            contentDescription = "Launcher",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape)
        )
    }

    NavigatorEffectFlow(viewModel.effectFlow) { effect ->
        when (effect.screen) {
            Screens.DashboardScreen.route ->
                navigator.navigateToHome()
        }
    }
}