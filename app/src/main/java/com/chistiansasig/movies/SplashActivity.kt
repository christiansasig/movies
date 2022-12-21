package com.chistiansasig.movies

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.plusAssign
import com.chistiansasig.movies.navgraph.Root
import com.chistiansasig.movies.navgraph.SplashNavigator
import com.christiansasig.movies.uikit.style.MoviesTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.NestedNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    EnglishForDevelopersApp(
                        activity = this@SplashActivity,
                        application = application
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
    @Composable
    fun EnglishForDevelopersApp(
        activity: SplashActivity,
        application: Application
    ) {
        val engine = rememberAnimatedNavHostEngine(
            navHostContentAlignment = Alignment.TopCenter,
            rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING,
            defaultAnimationsForNestedNavGraph = mapOf(
                Root to NestedNavGraphDefaultAnimations(
                    enterTransition = { fadeIn(animationSpec = tween(2000)) },
                    exitTransition = { fadeOut(animationSpec = tween(2000)) }
                ),
            )
        )
        val navController = engine.rememberNavController()

        val bottomSheetNavigator = rememberBottomSheetNavigator()
        navController.navigatorProvider += bottomSheetNavigator

        ModalBottomSheetLayout(
            bottomSheetNavigator,
            sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        ) {
            DestinationsNavHost(
                navController = navController,
                navGraph = Root,
                engine = engine,
                dependenciesContainerBuilder = {
                    dependency(
                        SplashNavigator(navController, activity)
                    )
                    dependency(activity)
                    dependency(application)
                }
            )
        }

    }
}
