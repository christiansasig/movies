package com.christiansasig.movies.dashboard.widget

import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.christiansasig.movies.dashboard.navitems.DashboardNavigationItems
import com.ramcosta.composedestinations.navigation.navigate

@Composable fun DashboardBottomNavigationWidget(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        DashboardNavigationItems.values().forEach { destination ->
            val isCurrentDestination =
                currentDestination?.hierarchy?.any {
                    it.route == destination.direction.route
                } == true

            NavigationBarItem(
                selected = isCurrentDestination,
                icon = {
                    Image(
                        painter = painterResource(
                            id = if (isCurrentDestination) {
                                destination.selectedIcon
                            } else {
                                destination.icon
                            }
                        ),
                        contentDescription = null,
                    )
                },
                onClick = {
                    navController.navigate(destination.direction) {
                        launchSingleTop = true
                        restoreState = true

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
                label = {
                    Text(text = stringResource(id = destination.label))
                }
            )
        }
    }
}