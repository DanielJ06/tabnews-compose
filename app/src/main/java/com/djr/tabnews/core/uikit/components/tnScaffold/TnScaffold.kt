package com.djr.tabnews.core.uikit.components.tnScaffold

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@Composable
fun TnScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isError: Boolean = false,
    content: (@Composable () -> Unit)
) {
    Box(modifier = modifier) {

        AnimatedVisibility(
            visible = isLoading && isError.not(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            TnLoading()
        }
        AnimatedVisibility(
            visible = isLoading.not() && isError.not(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            content.invoke()
        }
    }
}

@Composable
fun TnErrorScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Oops, something went wrong!",
            style = TabNewsTheme.typography.HeadingH3,
            color = TabNewsTheme.colors.textLight
        )
    }
}

@Composable
fun TnLoading() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Loading...",
            style = TabNewsTheme.typography.HeadingH4,
            color = TabNewsTheme.colors.textLight
        )
    }
}