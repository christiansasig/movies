package com.christiansasig.movies.dashboard

import android.annotation.SuppressLint
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
import com.christiansasig.movies.core.navigation.DashboardScreens
import com.christiansasig.movies.core.navigation.Screens.DashboardScreen.ROUTE
import com.christiansasig.movies.dashboard.navigation.DashboardNavigator
import com.christiansasig.movies.dashboard.navigation.config.DashboardRoutersConfig
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
class DashboardActivity : ComponentActivity() {
    var route: String = DashboardScreens.Home.route

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getStringExtra(ROUTE)?.let {
            route = it
        }

        setContent {
            MoviesTheme(dynamicColor = false) {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    DashboardApp(
                        startDestination = route,
                    )
                }
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
    @Composable fun DashboardApp(startDestination: String) {
        val navHostEngine = rememberAnimatedNavHostEngine(
            navHostContentAlignment = Alignment.TopCenter,
            rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING,
            defaultAnimationsForNestedNavGraph = mapOf(
                DashboardRoutersConfig to NestedNavGraphDefaultAnimations(
                    enterTransition = { fadeIn(animationSpec = tween(2000)) },
                    exitTransition = { fadeOut(animationSpec = tween(2000)) }
                ),
            )
        )

        val navController = navHostEngine.rememberNavController()

        val bottomSheetNavigator = rememberBottomSheetNavigator()
        navController.navigatorProvider += bottomSheetNavigator


            ModalBottomSheetLayout(
                bottomSheetNavigator = bottomSheetNavigator,
                sheetShape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp),
            ) {
                DestinationsNavHost(
                    engine = navHostEngine,
                    navController = navController,
                    navGraph = DashboardRoutersConfig,
                    dependenciesContainerBuilder = {
                        dependency(startDestination)
                        dependency(this@DashboardActivity)
                        dependency(
                            DashboardNavigator(
                                navController = navController,
                                context = this@DashboardActivity,
                            )
                        )
                    }
                )
            }

    }
}







