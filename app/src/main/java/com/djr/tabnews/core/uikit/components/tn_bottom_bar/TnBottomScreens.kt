package com.djr.tabnews.core.uikit.components.tn_bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.djr.tabnews.core.navigation.GraphDestinations

sealed class TnBottomScreens(
    val route: String,
    val icon: ImageVector,
    val allRoutes: List<String>
) {

    fun verifyRoutes(route: String?): Boolean {
        allRoutes.forEach {
            if (route == it) {
                return true
            }
        }
        return false
    }

    object Home : TnBottomScreens(
        route = GraphDestinations.MainRoutes.Main.createRoute(),
        icon = Icons.Default.Home,
        allRoutes = listOf(
            GraphDestinations.MainRoutes.Main.createRoute(),
            GraphDestinations.MainRoutes.ContentDetails.createRoute()
        )
    )

    object Bookmark : TnBottomScreens(
        route = GraphDestinations.BookmarkRoutes.Bookmarked.createRoute(),
        icon = Icons.Default.Favorite,
        allRoutes = listOf(
            GraphDestinations.BookmarkRoutes.Bookmarked.createRoute()
        )
    )

}