package com.djr.tabnews.core.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.djr.tabnews.core.navigation.GraphDestinations
import com.djr.tabnews.core.navigation.RootDestinations
import com.djr.tabnews.features.bookmark.BookmarkScreen

fun NavGraphBuilder.addBookmarkNavGraph() {
    navigation(
        route = RootDestinations.Bookmark.route,
        startDestination = GraphDestinations.BookmarkRoutes.Bookmarked.createRoute()
    ) {
        addBookmarkScreen()
    }
}

fun NavGraphBuilder.addBookmarkScreen() {
    composable(
        route = GraphDestinations.BookmarkRoutes.Bookmarked.createRoute()
    ) {
        BookmarkScreen()
    }
}
