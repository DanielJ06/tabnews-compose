package com.djr.tabnews.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.djr.tabnews.uikit.theme.tokens.TabNewsColors
import com.djr.tabnews.uikit.theme.tokens.TabNewsSpacing

val LocalColors = compositionLocalOf { TabNewsColors() }
val LocalSpacing = compositionLocalOf { TabNewsSpacing() }

@Composable
fun TabNewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    appContent: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides TabNewsColors(),
        LocalSpacing provides TabNewsSpacing()
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
}