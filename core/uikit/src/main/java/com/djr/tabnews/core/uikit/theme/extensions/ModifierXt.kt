package com.djr.tabnews.core.uikit.theme.extensions

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

fun Modifier.quarkCorners(): Modifier = composed {
    clip(
        RoundedCornerShape(TabNewsTheme.spacing.Quark)
    )
}

fun Modifier.nanoCorners(): Modifier = composed {
    clip(
        RoundedCornerShape(TabNewsTheme.spacing.Nano)
    )
}

fun Modifier.miniCorners(): Modifier = composed {
    clip(
        RoundedCornerShape(TabNewsTheme.spacing.Mini)
    )
}

fun Modifier.xxxsCorners(): Modifier = composed {
    clip(
        RoundedCornerShape(TabNewsTheme.spacing.Xxxs)
    )
}
