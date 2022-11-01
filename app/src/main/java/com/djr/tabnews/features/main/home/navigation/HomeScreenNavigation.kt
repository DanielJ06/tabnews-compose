package com.djr.tabnews.features.main.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.djr.tabnews.features.main.home.HomeRoute

const val homeRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.addHomeScreen(
    navigateToDetails: (postId: String) -> Unit
) {
    composable(route = homeRoute) {
        HomeRoute(navigateToDetails = navigateToDetails)
    }
}