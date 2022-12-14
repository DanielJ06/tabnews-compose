package com.djr.tabnews.features.main.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@Composable
fun HomeRoute(
    navigateToDetails: (postId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    HomeScreen(
        navigateToDetails = navigateToDetails,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    navigateToDetails: (postId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = TabNewsTheme.colors.primaryBg)
    ) {
        val happy = true
        val mood = if (happy) ":D" else ":("
        Text(
            text = "HomeScreen $mood",
            color = TabNewsTheme.colors.textNeutral,
            modifier = Modifier.clickable {
                navigateToDetails("temp_id")
            }
        )
    }
}