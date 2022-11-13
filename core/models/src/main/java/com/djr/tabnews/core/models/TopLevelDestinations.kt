package com.djr.tabnews.core.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val destinationIcon: ImageVector
) {
    MAIN(
        destinationIcon = Icons.Default.Home
    ),
    BOOKMARK(
        destinationIcon = Icons.Default.Favorite
    )
}