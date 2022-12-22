package com.christiansasig.movies.dashboard.navitems

import androidx.annotation.DrawableRes
import com.christiansasig.movies.core.R
import com.christiansasig.movies.dashboard.navigation.config.DashboardHomeNavGraphs
import com.christiansasig.movies.dashboard.navigation.config.DashboardMovieNavGraphs
import com.christiansasig.movies.dashboard.navigation.config.DashboardTvShowNavGraphs
import com.ramcosta.composedestinations.spec.NavGraphSpec

enum class DashboardNavigationItems(
    val direction: NavGraphSpec,
    val route: String,
    val label: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int
) {
    DashboardHome(
        DashboardHomeNavGraphs,
        "dashboard_home_screen",
        R.string.home_title,
        R.drawable.ic_home,
        R.drawable.ic_home_active
    ),
    DashboardProposal(
        DashboardMovieNavGraphs,
        "dashboard_movies_screen",
        R.string.movie_title,
        R.drawable.ic_movies,
        R.drawable.ic_movies_active
    ),
    DashboardSchedule(
        DashboardTvShowNavGraphs,
        "dashboard_tvshow_screen",
        R.string.tv_show_title,
        R.drawable.ic_tv,
        R.drawable.ic_tv_active
    ),
   ;

    companion object {
        fun navGraphFromRoute(route: String): DashboardNavigationItems? =
            values().find { it.route == route }
    }
}