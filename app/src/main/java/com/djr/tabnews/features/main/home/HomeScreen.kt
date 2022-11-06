package com.djr.tabnews.features.main.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.djr.tabnews.core.uikit.components.postCard.PostCard
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@Composable
fun HomeRoute(
    navigateToDetails: (postId: String) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.postState.collectAsState()

    HomeScreen(
        navigateToDetails = navigateToDetails,
        modifier = modifier,
        state = state
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToDetails = {}, state = HomeState()
    )
}

@Composable
fun HomeScreen(
    navigateToDetails: (postId: String) -> Unit,
    modifier: Modifier = Modifier,
    state: HomeState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = TabNewsTheme.colors.primaryBg)
    ) {

        if (state.isLoading) {
            Text(text = "Carregando...")
        }

        if (state.posts.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(
                    all = TabNewsTheme.spacing.Xxxs
                )
            ) {
                items(state.posts) { post ->
                    Column(
                        modifier = Modifier
                            .padding(
                                vertical = TabNewsTheme.spacing.Nano
                            )
                    ) {
                        PostCard(postItem = post)
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        BottomCenter
    ) {
        Spacer(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            TabNewsTheme.colors.primaryBg,
                        )
                    )
                )
        )
    }

}