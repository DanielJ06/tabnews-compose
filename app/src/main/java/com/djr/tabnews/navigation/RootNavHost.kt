package com.djr.tabnews.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.djr.tabnews.features.auth.graph.addAuthGraph
import com.djr.tabnews.features.auth.graph.authGraphRoute
import com.djr.tabnews.features.bookmark.bookmarks.navigation.addBookmarksScreen
import com.djr.tabnews.features.main.graph.addMainGraph
import com.djr.tabnews.features.main.graph.navigateToMainGraph

@Composable
fun RootNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = authGraphRoute,
        modifier = modifier
    ) {
        addAuthGraph {
            navController.navigateToMainGraph(
                navOptions {
                    navController.popBackStack()
                }
            )
        }
        addMainGraph(navController)
        addBookmarksScreen()
    }
}