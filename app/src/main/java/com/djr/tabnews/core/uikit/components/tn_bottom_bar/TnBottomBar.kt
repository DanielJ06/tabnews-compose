package com.djr.tabnews.core.uikit.components.tn_bottom_bar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.djr.tabnews.core.navigation.TopLevelDestination
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import kotlinx.coroutines.launch

@Composable
fun TnBottomBar(
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToTopLevel: (TopLevelDestination) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val iconCoordinates = remember { Animatable(0f) }

    Box(
        modifier = Modifier
            .background(TabNewsTheme.colors.secondaryBg)
    ) {
        BottomNavigation(
            backgroundColor = TabNewsTheme.colors.secondaryBg,
        ) {
            destinations.forEach { screen ->
                val isSelected = currentDestination.isTopLevelDestinationInHierarchy(screen)

                val iconTint = if (isSelected) {
                    TabNewsTheme.colors.accentPrimary
                } else {
                    TabNewsTheme.colors.textNeutral.copy(alpha = 0.5f)
                }

                BottomNavItem(
                    isSelected = isSelected,
                    screen = screen,
                    iconTint = iconTint,
                    onClick = { onNavigateToTopLevel(screen) },
                    onPositioned = { axis ->
                        coroutineScope.launch {
                            if (
                                iconCoordinates.value == 0f &&
                                destinations[0] == screen
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
        FloatingIndicatorPill(
            indicatorColor = TabNewsTheme.colors.accentPrimary,
            pillSize = Size(width = 100f, height = 10.dp.value),
            axisXCoordinates = iconCoordinates.value
        )
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
    screen: TopLevelDestination,
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
                imageVector = screen.destinationIcon,
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

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
