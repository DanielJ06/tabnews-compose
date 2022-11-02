package com.djr.tabnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.djr.tabnews.core.navigation.AppNavigation
import com.djr.tabnews.core.navigation.TnNavHost
import com.djr.tabnews.core.uikit.components.tn_bottom_bar.TnBottomBar

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val appNavigation = AppNavigation(navController)
            var bottomBarVisible by rememberSaveable { mutableStateOf(true) }

            LaunchedEffect(navBackStackEntry?.destination?.route) {
                navBackStackEntry?.destination?.route?.let {
                    bottomBarVisible = appNavigation.canShowBottomBar(it)
                }
            }

            Scaffold(
                bottomBar = {
                    AnimatedVisibility(
                        visible = bottomBarVisible,
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it })
                    ) {
                        TnBottomBar(
                            destinations = appNavigation.destinations,
                            currentDestination = appNavigation.currentDestination,
                            onNavigateToTopLevel = appNavigation::onNavigateToTopDestination
                        )
                    }
                }
            ) {
                TnNavHost(navController = navController)
            }
        }
    }
}
