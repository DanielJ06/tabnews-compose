package com.djr.tabnews.core.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.djr.tabnews.core.uikit.theme.tokens.*

val LocalColors = compositionLocalOf { PURPLE_THEME }
val LocalSpacing = compositionLocalOf { TabNewsSpacing() }
val LocalTypography = compositionLocalOf { TabNewsTypography() }

@Composable
fun TabNewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    appContent: @Composable () -> Unit
) {
    val randomScheme = HighlightSchemes
        .values()
        .asList()
        .shuffled()
        .first()

    val selectedTheme = when (randomScheme) {
        HighlightSchemes.PURPLE_SCHEME -> PURPLE_THEME
        HighlightSchemes.CYAN_SCHEME -> CYAN_THEME
        HighlightSchemes.PINK_SCHEME -> PINK_THEME
        HighlightSchemes.YELLOW_SCHEME -> YELLOW_THEME
    }

    CompositionLocalProvider(
        LocalColors provides selectedTheme,
        LocalSpacing provides TabNewsSpacing(),
        LocalTypography provides TabNewsTypography()
    ) {
        MaterialTheme(
            content = appContent
        )
    }
}

object TabNewsTheme {
    val colors: TabNewsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val spacing: TabNewsSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current
    val typography: TabNewsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}