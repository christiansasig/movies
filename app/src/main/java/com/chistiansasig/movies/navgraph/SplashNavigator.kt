package com.chistiansasig.movies.navgraph

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.navigation.NavController
import com.chistiansasig.movies.navigator.SplashNavigator
import com.christiansasig.movies.core.navigation.DashboardScreens
import com.christiansasig.movies.core.navigation.Screens.DashboardScreen.ROUTE
import com.christiansasig.movies.dashboard.DashboardActivity

class SplashNavigator(
    private val navController: NavController,
    private val activity: ComponentActivity
) : SplashNavigator {

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun navigateToHome() {
        activity.startActivity(
            Intent(activity, DashboardActivity::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra(ROUTE, DashboardScreens.Home.route)
                }
        )
    }
}