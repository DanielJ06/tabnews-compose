package com.djr.tabnews.features.main.post.postDetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.djr.tabnews.core.uikit.components.markdownWrapper.Markdown
import com.djr.tabnews.core.uikit.components.tnScaffold.TnScaffold
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.djr.tabnews.features.main.post.postDetails.components.PostActions
import com.djr.tabnews.features.main.post.postDetails.components.PostHeader
import com.djr.tabnews.features.main.post.postDetails.components.PostReply

@Composable
fun PostDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: PostDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.handleGetPostDetail()
        viewModel.handleGetPostReplies()
    }

    val postDetailsState by viewModel.postDetailState.collectAsState()
    val postRepliesState by viewModel.postRepliesState.collectAsState()

    PostDetailsScreen(modifier, postDetailsState, postRepliesState)
}

@Composable
fun PostDetailsScreen(
    modifier: Modifier,
    postDetailsState: PostDetailsState,
    postRepliesState: PostRepliesState
) {
    TnScaffold(
        modifier = modifier
            .background(color = TabNewsTheme.colors.primaryBg)
            .fillMaxSize(),
        isLoading = postDetailsState.isLoading,
        isError = postDetailsState.error.isNotEmpty()
    ) {
        AnimatedVisibility(
            visible = postDetailsState.postDetail != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            val scrollState = rememberLazyListState()
            postDetailsState.postDetail?.let {
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = TabNewsTheme.spacing.Xxs,
                                    vertical = TabNewsTheme.spacing.Mini,
                                )
                        ) {
                            PostHeader(postContent = it)
                            Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Xxs))
                            Markdown(
                                markdown = it.body,
                                style = TabNewsTheme.typography.TextNormal,
                                color = TabNewsTheme.colors.textNeutralLight,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Xxs))
                        }
                        PostActions(postContent = it)
                    }
                    if (postRepliesState.postReplies.isNotEmpty()) {
                        itemsIndexed(postRepliesState.postReplies) { index, reply ->
                            if (index == 0) Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = TabNewsTheme.spacing.Xxxs,
                                        vertical = TabNewsTheme.spacing.Nano,
                                    )
                            ) {
                                PostReply(postReplies = reply)
                            }
                        }
                    }
                }
            }
        }
    }
}
