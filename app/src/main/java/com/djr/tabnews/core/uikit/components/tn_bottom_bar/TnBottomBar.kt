package com.djr.tabnews.core.uikit.components.tn_bottom_bar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import kotlinx.coroutines.launch

@Composable
fun TnBottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val coroutineScope = rememberCoroutineScope()

    val screens = listOf(
        TnBottomScreens.Home,
        TnBottomScreens.Bookmark,
        TnBottomScreens.Teste
    )

    val iconCoordination = remember { Animatable(0f) }

    val indicatorColor = TabNewsTheme.colors.highlight
    val dpT = 8.dp

    Column {

        Canvas(
            modifier = Modifier
                .height(dpT)
                .fillMaxWidth()
        ) {
            drawRoundRect(
                color = indicatorColor,
                size = Size(width = 66f, height = dpT.toPx()),
                topLeft = Offset(x = iconCoordination.value, y = 0f),
                cornerRadius = CornerRadius(33f, 33f)
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

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

                var selectedPosition = 0f

                if (isSelected) {
                    coroutineScope.launch {
                        iconCoordination.animateTo(
                            selectedPosition,
                            animationSpec = tween(
                                durationMillis = 600,
                                easing = CubicBezierEasing(.57f, .21f, .69f, 1.25f)
                            )
                        )
                    }
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
                                .onGloballyPositioned {
                                    coroutineScope.launch {
                                        if (
                                            iconCoordination.value == 0f &&
                                            screens[0] == screen
                                        ) iconCoordination.animateTo(
                                            it.positionInRoot().x
                                        )
                                    }
                                    selectedPosition = it.positionInRoot().x
                                }
                        )
                    },
                    onClick = {

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
}