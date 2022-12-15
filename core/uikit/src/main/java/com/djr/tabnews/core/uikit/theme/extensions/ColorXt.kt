package com.djr.tabnews.core.uikit.theme.extensions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red

fun Color.darken(factor: Float): Color {
    val a = this.toArgb().alpha
    val r = (this.toArgb().red * factor).toInt()
    val g = (this.toArgb().green * factor).toInt()
    val b = (this.toArgb().blue * factor).toInt()
    return Color(r, g, b, a)
}