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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
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

    val iconCoordinates = remember { Animatable(0f) }

    val topDestinationScreens = listOf(
        TnBottomScreens.Home,
        TnBottomScreens.Bookmark
    )

    Column {
        FloatingIndicatorPill(
            indicatorColor = TabNewsTheme.colors.highlight,
            pillSize = Size(width = 150f, height = 8.dp.value),
            axisXCoordinates = iconCoordinates.value
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
            topDestinationScreens.forEach { screen ->
                val currentDestination = navBackStackEntry?.destination
                val isSelected = currentDestination?.hierarchy?.any {
                    screen.verifyRoutes(it.route)
                } == true

                val iconTint = if (isSelected) {
                    TabNewsTheme.colors.highlight
                } else {
                    TabNewsTheme.colors.textNeutral
                }

                fun itemClick() {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }

                BottomNavItem(
                    isSelected = isSelected,
                    screen = screen,
                    iconTint = iconTint,
                    onClick = { itemClick() },
                    onPositioned = { axis ->
                        coroutineScope.launch {
                            if (
                                iconCoordinates.value == 0f &&
                                topDestinationScreens[0] == screen
                            ) iconCoordinates.snapTo(axis)
                        }
                    },
                    onSelected = { axis ->
                        coroutineScope.launch {
                            iconCoordinates.animateTo(
                                axis,
                                animationSpec = tween(
                                    durationMillis = 150,
                                    easing = CubicBezierEasing(.57f, .21f, .69f, 1.25f)
                                )
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun FloatingIndicatorPill(
    modifier: Modifier = Modifier,
    indicatorColor: Color,
    pillSize: Size,
    axisXCoordinates: Float
) {
    Canvas(
        modifier = modifier
            .height(pillSize.height.dp)
            .fillMaxWidth()
    ) {
        val pillCenter = pillSize.width / 2
        drawRoundRect(
            color = indicatorColor,
            size = pillSize,
            topLeft = Offset(x = axisXCoordinates - pillCenter, y = 0f),
            cornerRadius = CornerRadius(
                y = (pillSize.width / 2),
                x = (pillSize.height / 2)
            )
        )
    }
}

@Composable
fun RowScope.BottomNavItem(
    isSelected: Boolean,
    screen: TnBottomScreens,
    iconTint: Color,
    onClick: () -> Unit,
    onPositioned: (icPosition: Float) -> Unit,
    onSelected: (icPosition: Float) -> Unit
) {
    var selectedAxisX by remember { mutableStateOf(0f) }

    if (isSelected) {
        onSelected(selectedAxisX)
    }

    BottomNavigationItem(
        selected = isSelected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier
                    .size(TabNewsTheme.spacing.Xxs)
                    .onGloballyPositioned {
                        val axisX = it.positionInRoot().x
                        val icHalfSize = it.size.width / 2
                        val icCenter = axisX + icHalfSize
                        selectedAxisX = icCenter
                        onPositioned(icCenter)
                    }
            )
        }
    )
}