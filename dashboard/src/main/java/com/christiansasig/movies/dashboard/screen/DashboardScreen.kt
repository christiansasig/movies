package com.christiansasig.movies.dashboard.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.christiansasig.movies.core.navigation.DashboardScreens
import com.christiansasig.movies.dashboard.navigation.DashboardNavigator
import com.christiansasig.movies.dashboard.navigation.TabNavigator
import com.christiansasig.movies.dashboard.navigation.config.*
import com.christiansasig.movies.dashboard.widget.DashboardBottomNavigationWidget
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.dependency

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(
    start = true
)
@Destination
@Composable fun DashboardScreen(
    startDestination: String = DashboardScreens.Home.route,
    navigator: DashboardNavigator
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            DashboardBottomNavigationWidget(
                navController = navController
            )
        }
    ) { innerPadding ->
        DestinationsNavHost(
            navController = navController,
            navGraph = TabBarHomeRouter,
            modifier = Modifier.padding(innerPadding),
            startRoute = when (startDestination) {
                DashboardScreens.Home.route -> DashboardHomeNavGraphs
                DashboardScreens.Movie.route -> DashboardMovieNavGraphs
                DashboardScreens.TvShow.route -> DashboardTvShowNavGraphs
                else -> DashboardHomeNavGraphs
            },
            dependenciesContainerBuilder = {
                dependency(
                    navigator
                )
                dependency(
                    TabNavigator(
                        navControllerTab = navController
                    )
                )
            }
        )
    }
}