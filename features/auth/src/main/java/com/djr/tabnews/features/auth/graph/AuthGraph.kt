package com.djr.tabnews.features.auth.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.djr.tabnews.features.auth.login.addLoginScreen
import com.djr.tabnews.features.auth.login.loginRoute

const val authGraphRoute = "auth_route"
val hideAuthBottomNavIn = listOf(
    loginRoute
)

fun NavGraphBuilder.addAuthGraph(
    onNavigateToMain: () -> Unit
) {
    navigation(
        route = authGraphRoute,
        startDestination = loginRoute
    ) {
        addLoginScreen(onNavigateToMain)
    }
}