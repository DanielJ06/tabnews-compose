package com.djr.tabnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.djr.tabnews.core.navigation.RootDestinations
import com.djr.tabnews.core.navigation.graphs.addBookmarkNavGraph
import com.djr.tabnews.core.navigation.graphs.addHomeNavGraph
import com.djr.tabnews.core.uikit.components.TabBottomBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = { TabBottomBar(navController) }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = RootDestinations.Main.route
                ) {
                    addHomeNavGraph(navController)
                    addBookmarkNavGraph()
                }
            }
        }
    }
}
