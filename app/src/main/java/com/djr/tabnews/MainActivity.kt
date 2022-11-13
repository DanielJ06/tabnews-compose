package com.djr.tabnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.djr.tabnews.navigation.AppNavigation
import com.djr.tabnews.navigation.TnNavHost
import com.djr.tabnews.core.uikit.components.tnBottomBar.TnBottomBar
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabNewsTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val systemUi = rememberSystemUiController()
                val appNavigation = AppNavigation(navController)
                var bottomBarVisible by rememberSaveable { mutableStateOf(true) }

                LaunchedEffect(navBackStackEntry?.destination?.route) {
                    navBackStackEntry?.destination?.route?.let {
                        bottomBarVisible = appNavigation.canShowBottomBar(it)
                    }
                }

                systemUi.setSystemBarsColor(
                    TabNewsTheme.colors.primaryBg
                )

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
                    TnNavHost(
                        navController = navController,
                        modifier = Modifier
                            .padding(if (bottomBarVisible) it else PaddingValues())
                            .consumedWindowInsets(if (bottomBarVisible) it else PaddingValues())
                    )
                }
            }
        }
    }
}
