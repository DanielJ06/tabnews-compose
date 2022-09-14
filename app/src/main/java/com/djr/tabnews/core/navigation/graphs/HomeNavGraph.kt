package com.djr.tabnews.core.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.djr.tabnews.core.navigation.GraphDestinations
import com.djr.tabnews.core.navigation.RootDestinations
import com.djr.tabnews.features.main.home.HomeScreen
import com.djr.tabnews.features.main.postDetails.PostDetailsScreen

fun NavGraphBuilder.addHomeNavGraph(navController: NavHostController) {
    navigation(
        route = RootDestinations.Main.route,
        startDestination = GraphDestinations.MainRoutes.Main.createRoute()
    ) {
        addHomeScreen(navController)
        addContentDetailsScreen()
    }
}

fun NavGraphBuilder.addContentDetailsScreen() {
    composable(
        route = GraphDestinations.MainRoutes.ContentDetails.createRoute(),
        arguments = GraphDestinations.MainRoutes.ContentDetails.arguments
    ) {
        PostDetailsScreen()
    }
}

fun NavGraphBuilder.addHomeScreen(navController: NavHostController) {
    composable(
        route = GraphDestinations.MainRoutes.Main.createRoute()
    ) {
        HomeScreen {
            navController.navigate(
                GraphDestinations.MainRoutes.ContentDetails.createRoute()
            )
        }
    }
}