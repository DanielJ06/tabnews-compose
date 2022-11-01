package com.djr.tabnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.djr.tabnews.core.navigation.AppNavigation
import com.djr.tabnews.core.navigation.TnNavHost
import com.djr.tabnews.core.uikit.components.tn_bottom_bar.TnBottomBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val appNavigation = AppNavigation(navController)

            Scaffold(
                bottomBar = {
                    TnBottomBar(
                        destinations = appNavigation.destinations,
                        currentDestination = appNavigation.currentDestination,
                        onNavigateToTopLevel = appNavigation::onNavigateToTopDestination
                    )
                }
            ) {
                TnNavHost(navController = navController)
            }
        }
    }
}
