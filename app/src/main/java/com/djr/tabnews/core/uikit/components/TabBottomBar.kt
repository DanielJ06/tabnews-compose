package com.djr.tabnews.core.uikit.components

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@Composable
fun TabBottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val screens = listOf(
        TabBottomScreens.Home,
        TabBottomScreens.Bookmark
    )

    BottomNavigation(
        backgroundColor = TabNewsTheme.colors.secondaryBg,
        contentColor = TabNewsTheme.colors.borderLight,
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = TabNewsTheme.spacing.Xxxs,
                    topEnd = TabNewsTheme.spacing.Xxxs
                )
            )
    ) {
        screens.forEach { screen ->
            val currentDestination = navBackStackEntry?.destination
            val isSelected = currentDestination?.hierarchy?.any {
                screen.verifyRoutes(it.route)
            } == true

            val iconTint = if (isSelected) {
                TabNewsTheme.colors.highlight
            } else {
                TabNewsTheme.colors.textNeutral
            }

            BottomNavigationItem(
                selected = isSelected,
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier
                            .size(TabNewsTheme.spacing.Xxs)
                    )
                },
                onClick = {
                    Log.i("TESTE", "TabBottomBar: ${currentDestination?.hierarchy}")
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}