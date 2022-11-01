package com.djr.tabnews.features.main.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.djr.tabnews.features.main.home.navigation.addHomeScreen
import com.djr.tabnews.features.main.home.navigation.homeRoute
import com.djr.tabnews.features.main.postDetails.navigation.addProfileDetailsScreen
import com.djr.tabnews.features.main.postDetails.navigation.navigateToProfileDetails

const val mainGraphRoute = "main_graph"

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
        addHomeScreen { postId -> navController.navigateToProfileDetails(postId) }
        addProfileDetailsScreen()
    }
}
