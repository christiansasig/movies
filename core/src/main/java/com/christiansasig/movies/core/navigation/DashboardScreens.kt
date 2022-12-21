package com.christiansasig.movies.core.navigation


sealed class DashboardScreens(val route: String) {
    object Home : DashboardScreens("Home")
    object Movie : DashboardScreens("Movie")
    object TvShow : DashboardScreens("TvShow")
}