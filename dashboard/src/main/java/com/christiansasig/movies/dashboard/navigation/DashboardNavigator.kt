package com.christiansasig.movies.dashboard.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.christiansasig.movies.home.navigator.DashboardHomeNavigatorProvider
import com.christiansasig.movies.movie.navigator.DashboardMovieNavigatorProvider
import com.christiansasig.movies.movie.navigator.DashboardTvShowNavigatorProvider


data class DashboardNavigator(
    val navController: NavController,
    val context: Context
) : DashboardHomeNavigatorProvider,
    DashboardMovieNavigatorProvider,
    DashboardTvShowNavigatorProvider
{
    override fun onBackPressedListener() {
        navController.popBackStack()
    }

    override fun goToUrlWebView(webUrl: String) {
        if (URLUtil.isValidUrl(webUrl)) {
            ContextCompat.startActivity(
                context,
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(webUrl)
                ),
                null
            )
        }
    }
}