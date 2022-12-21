package com.chistiansasig.movies.navgraph

import com.chistiansasig.movies.screen.SplashNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

val Root = object : NavGraphSpec {
    override val route = "root"

    override val startRoute = SplashNavGraph

    override val destinationsByRoute =
        emptyMap<String, DestinationSpec<*>>()

    override val nestedNavGraphs =
        listOf(
            SplashNavGraph,
        )
}