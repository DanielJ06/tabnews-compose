package com.djr.tabnews.features.bookmark.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@Composable
fun BookmarksScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = TabNewsTheme.colors.primaryBg)
    ) {
        Text(
            text = "BookmarkScreen",
            color = TabNewsTheme.colors.textNeutral
        )
    }
}