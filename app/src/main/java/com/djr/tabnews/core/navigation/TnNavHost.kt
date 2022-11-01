package com.djr.tabnews.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.djr.tabnews.features.bookmark.bookmarks.navigation.addBookmarksScreen
import com.djr.tabnews.features.main.graph.addMainGraph
import com.djr.tabnews.features.main.graph.mainGraphRoute

@Composable
fun TnNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = mainGraphRoute
    ) {
        addMainGraph(navController)
        addBookmarksScreen()
    }
}