package com.djr.tabnews.features.bookmark.bookmarks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.djr.tabnews.features.bookmark.bookmarks.BookmarksScreen

const val bookmarksRoute = "bookmarks_route"

fun NavController.navigateToBookmarks(navOptions: NavOptions? = null) {
    this.navigate(bookmarksRoute, navOptions)
}

fun NavGraphBuilder.addBookmarksScreen() {
    composable(bookmarksRoute) {
        BookmarksScreen()
    }
}