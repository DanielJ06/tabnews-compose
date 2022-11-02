package com.djr.tabnews.core.uikit.theme.tokens

import androidx.compose.ui.graphics.Color

data class TabNewsColors(
    val primaryBg: Color = Color(0xFF1B1F23),
    val secondaryBg: Color = Color(0xFF24292E),

    val accentPrimary: Color,
    val accentSecondary: Color,
    val accentFade: List<Color> = listOf(accentPrimary, accentSecondary),

    val borderDark: Color = Color(0xFF444D56),
    val borderNeutral: Color = Color(0xFFD1D5DA),
    val borderLight: Color = Color(0xFFFFFFFF),

    val textDarker: Color = Color(0xFF24292E),
    val textDark: Color = Color(0xFF586069),
    val textNeutral: Color = Color(0xFF6A737D),
    val textLight: Color = Color(0xFFFFFFFF),

    val tabMoney: Color = Color(0xFF0366D6),
    val tabCash: Color = Color(0xFF34D058),
)

private val CYAN = Color(0xFF80FFEA)
private val GREEN = Color(0xFF8AFF80)
private val PINK = Color(0xFFFF80BF)
private val PURPLE = Color(0xFF9580FF)
private val YELLOW = Color(0xFFFFFF80)

val PURPLE_THEME = TabNewsColors(accentPrimary = PURPLE, accentSecondary = CYAN)
val YELLOW_THEME = TabNewsColors(accentPrimary = YELLOW, accentSecondary = PINK)
val CYAN_THEME = TabNewsColors(accentPrimary = CYAN, accentSecondary = GREEN)
val PINK_THEME = TabNewsColors(accentPrimary = PINK, accentSecondary = PURPLE)


enum class HighlightSchemes {
    PURPLE_SCHEME,
    YELLOW_SCHEME,
    CYAN_SCHEME,
    PINK_SCHEME
}