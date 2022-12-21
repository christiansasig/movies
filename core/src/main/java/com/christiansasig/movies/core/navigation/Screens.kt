package com.christiansasig.movies.core.navigation

sealed class Screens(val route: String) {

    object LoginScreen : Screens("LoginScreen")
    object DashboardScreen : Screens("DashboardScreen") {
        const val ROUTE = "route"
        const val DEEPLINK = "deeplink"
    }
    object SplashScreen : Screens("SplashScreen") {
        const val START_DESTINATION = "startDestination"
    }
}