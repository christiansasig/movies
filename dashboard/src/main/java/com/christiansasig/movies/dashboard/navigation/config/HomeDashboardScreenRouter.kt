package com.christiansasig.movies.dashboard.navigation.config

import com.christiansasig.movies.dashboard.screen.DashboardNavGraph
import com.christiansasig.movies.home.screen.destinations.DashboardHomeScreenDestination
import com.christiansasig.movies.movie.screen.destinations.DashboardMovieScreenDestination
import com.christiansasig.movies.movie.screen.destinations.DashboardTvShowScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

val TabBarHomeRouter = object : NavGraphSpec {
    override val route: String
        get() = "tab-bar-root"

    override val startRoute: Route
        get() = DashboardHomeNavGraphs

    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = emptyMap()

    override val nestedNavGraphs: List<NavGraphSpec>
        get() =
            listOf(
                DashboardHomeNavGraphs,
                DashboardMovieNavGraphs,
                DashboardTvShowNavGraphs
            )
}

val DashboardRoutersConfig = object : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = emptyMap()

    override val route: String
        get() = "navgraph-router-dashboard"

    override val startRoute: Route
        get() = DashboardNavGraph

    override val nestedNavGraphs: List<NavGraphSpec>
        get() = listOf(
            DashboardNavGraph,
        )
}

internal val DashboardHomeNavGraphs = object : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() =
            listOf(
                DashboardHomeScreenDestination,
            )
                .associateBy {
                    it.route
                }

    override val route: String
        get() = "navgraph-router-dashboard-home"

    override val startRoute: Route
        get() = DashboardHomeScreenDestination
}

internal val DashboardMovieNavGraphs = object : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() =
            listOf(
                DashboardMovieScreenDestination
            )
                .associateBy {
                    it.route
                }

    override val route: String
        get() = "navgraph-router-dashboard-movies"

    override val startRoute: Route
        get() = DashboardMovieScreenDestination
}

internal val DashboardTvShowNavGraphs = object : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() =
            listOf(
                DashboardTvShowScreenDestination
            )
                .associateBy {
                    it.route
                }

    override val route: String
        get() = "navgraph-router-dashboard-tvshow"

    override val startRoute: Route
        get() = DashboardTvShowScreenDestination
}



