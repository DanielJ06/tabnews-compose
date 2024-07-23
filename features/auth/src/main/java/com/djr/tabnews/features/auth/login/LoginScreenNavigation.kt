package com.djr.tabnews.features.auth.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val loginRoute = "login_route"

fun NavGraphBuilder.addLoginScreen(
    navigateToHome: () -> Unit
) {
    composable(loginRoute) {
        LoginRoute(navigateToHome = navigateToHome)
    }
}