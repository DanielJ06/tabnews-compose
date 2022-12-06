package com.djr.tabnews.features.main.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.djr.tabnews.features.main.home.navigation.addHomeScreen
import com.djr.tabnews.features.main.home.navigation.homeRoute
import com.djr.tabnews.features.main.post.postDetails.navigation.addPostDetailsScreen
import com.djr.tabnews.features.main.post.postDetails.navigation.navigateToPostDetails
import com.djr.tabnews.features.main.post.postDetails.navigation.postDetailRoutePattern
import com.djr.tabnews.features.main.post.replyChild.navigation.addReplyChildrenScreen
import com.djr.tabnews.features.main.post.replyChild.navigation.navigateToReplyChildrenScreen
import com.djr.tabnews.features.main.post.replyChild.navigation.replyChildrenPattern

const val mainGraphRoute = "main_graph"
val hideBottomNavIn = listOf(
    postDetailRoutePattern,
    replyChildrenPattern
)

fun NavController.navigateToMainGraph(navOptions: NavOptions? = null) {
    this.navigate(mainGraphRoute, navOptions)
}

fun NavGraphBuilder.addMainGraph(
    navController: NavController
) {
    navigation(
        route = mainGraphRoute,
        startDestination = homeRoute
    ) {
        addHomeScreen { owner, slug -> navController.navigateToPostDetails(owner, slug) }
        addPostDetailsScreen { thread -> navController.navigateToReplyChildrenScreen(thread) }
        addReplyChildrenScreen { thread -> navController.navigateToReplyChildrenScreen(thread) }
    }
}
