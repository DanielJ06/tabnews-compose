package com.djr.tabnews.core.navigation

import androidx.navigation.NamedNavArgument

sealed class RootDestinations(val route: String) {
    object Main : RootDestinations("main")
    object Bookmark : RootDestinations("bookmark")
}

sealed class GraphDestinations(
    val root: RootDestinations,
    val route: String,
) {

    open val arguments: List<NamedNavArgument> = emptyList()
    fun createRoute() = "${root.route}/$route"

    object BookmarkRoutes {
        object Bookmarked :
            GraphDestinations(root = RootDestinations.Bookmark, route = "bookmarked")
    }

    object MainRoutes {

        object Main : GraphDestinations(root = RootDestinations.Main, route = "home")

        object ContentDetails : GraphDestinations(
            root = RootDestinations.Main,
            route = "content"
        )

    }

}