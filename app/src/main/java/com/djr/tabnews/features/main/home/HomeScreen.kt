package com.djr.tabnews.features.main.home


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.djr.tabnews.core.uikit.components.postCard.PostCard
import com.djr.tabnews.core.uikit.components.tnScaffold.TnScaffold
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.djr.tabnews.domain.models.PostModel
import com.djr.tabnews.domain.models.dummies.DUMMY_POSTS
import kotlinx.coroutines.delay

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

@Composable
fun HomeScreen(
    navigateToDetails: (postId: String) -> Unit,
    modifier: Modifier = Modifier,
    state: HomeState
) {
    TnScaffold(
        modifier = modifier
            .background(color = TabNewsTheme.colors.primaryBg)
            .fillMaxSize(),
        isLoading = state.isLoading,
        isError = state.error.isNotEmpty()
    ) {
        AnimatedVisibility(
            visible = state.posts.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            HomeFeed(state.posts)
        }
    }
}

@Composable
fun HomeFeed(
    posts: List<PostModel>
) {
    var initialized by rememberSaveable { mutableStateOf(false) }
    val gradientAlpha by animateColorAsState(
        targetValue = if (initialized) {
            TabNewsTheme.colors.accentPrimary.copy(alpha = 0.09f)
        } else Color.Transparent,
        animationSpec = TweenSpec(
            durationMillis = 5000,
        )
    )

    LaunchedEffect(Unit) {
        delay(500)
        initialized = true
    }

    val gradient = listOf(Color.Transparent, gradientAlpha, Color.Transparent)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(gradient)
                )
        )
        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(TabNewsTheme.spacing.Xxs),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Tabnews",
                        style = TabNewsTheme.typography.TextLargeSB,
                        color = TabNewsTheme.colors.textNeutralLight,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Building a better piece of internet",
                        style = TabNewsTheme.typography.TextSmall,
                        color = TabNewsTheme.colors.textNeutralLight,
                    )
                }
            }
            items(posts) { post ->
                Column(
                    modifier = Modifier.padding(
                        vertical = TabNewsTheme.spacing.Nano,
                        horizontal = TabNewsTheme.spacing.Xxxs
                    )
                ) {
                    PostCard(postItem = post)
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
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToDetails = {},
        state = HomeState(isLoading = true, posts = DUMMY_POSTS)
    )
}