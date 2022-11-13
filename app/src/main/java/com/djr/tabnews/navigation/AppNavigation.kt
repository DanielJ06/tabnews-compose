package com.djr.tabnews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.djr.tabnews.core.models.TopLevelDestination
import com.djr.tabnews.features.bookmark.bookmarks.navigation.navigateToBookmarks
import com.djr.tabnews.features.main.graph.hideBottomNavIn
import com.djr.tabnews.features.main.graph.navigateToMainGraph

class AppNavigation(
    private val navController: NavController
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val destinations = TopLevelDestination.values().asList()

    private val routesWithoutBottomNav = listOf(
        hideBottomNavIn
    ).flatten()

    fun canShowBottomBar(route: String): Boolean {
        return routesWithoutBottomNav.any { it.contains(route, true) }.not()
    }

    fun onNavigateToTopDestination(destination: TopLevelDestination) {
        val topNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (destination) {
            TopLevelDestination.BOOKMARK -> navController.navigateToBookmarks(
                topNavOptions
            )
            TopLevelDestination.MAIN -> navController.navigateToMainGraph(topNavOptions)
        }
    }

}
