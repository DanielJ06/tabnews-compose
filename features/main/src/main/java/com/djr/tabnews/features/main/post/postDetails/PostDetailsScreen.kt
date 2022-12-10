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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.models.posts.PostThread
import com.djr.tabnews.core.uikit.components.markdownWrapper.Markdown
import com.djr.tabnews.core.uikit.components.tnScaffold.TnScaffold
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.djr.tabnews.features.main.post.postDetails.components.PostActions
import com.djr.tabnews.features.main.post.postDetails.components.PostHeader
import com.djr.tabnews.features.main.post.postDetails.components.PostReply

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PostDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: PostDetailsViewModel = hiltViewModel(),
    navigateToReplyChildren: (PostThread) -> Unit
) {
    val postDetailsState by viewModel.postDetailState.collectAsStateWithLifecycle()
    val postRepliesState by viewModel.postRepliesState.collectAsStateWithLifecycle()
    val isSavedState by viewModel.isPostSavedState.collectAsStateWithLifecycle()

    PostDetailsScreen(
        modifier,
        postDetailsState,
        postRepliesState,
        isSaved = isSavedState,
        navigateToReplyChildren = navigateToReplyChildren,
        onSaveClick = viewModel::handleToggleSave
    )
}

@Composable
fun PostDetailsScreen(
    modifier: Modifier,
    postDetailsState: PostDetailsState,
    postRepliesState: PostRepliesState,
    isSaved: Boolean,
    navigateToReplyChildren: (PostThread) -> Unit,
    onSaveClick: (post: PostContent) -> Unit
) {
    val scrollState = rememberLazyListState()

    TnScaffold(
        modifier = modifier
            .background(color = TabNewsTheme.colors.primaryBg)
            .fillMaxSize(),
        isLoading = postDetailsState.isLoading,
        isError = postDetailsState.error.isNotEmpty()
    ) {
        AnimatedVisibility(
            visible = postDetailsState.postDetail != null, enter = fadeIn(), exit = fadeOut()
        ) {
            postDetailsState.postDetail?.let {
                LazyColumn(
                    state = scrollState, modifier = Modifier.fillMaxWidth()
                ) {
                    item(key = it.id) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = TabNewsTheme.spacing.Xxs,
                                    vertical = TabNewsTheme.spacing.Mini,
                                )
                        ) {
                            PostHeader(
                                postContent = it,
                                onFollowClick = {},
                                onSaveClick = onSaveClick,
                                isSaved = isSaved
                            )
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
                    itemsIndexed(
                        items = postRepliesState.postReplies,
                        key = { _, reply -> reply.id }
                    ) { index, reply ->
                        if (index == 0) Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = TabNewsTheme.spacing.Xxxs,
                                    vertical = TabNewsTheme.spacing.Nano,
                                )
                        ) {
                            PostReply(postReplies = reply, seeMoreCb = {
                                navigateToReplyChildren(it)
                            })
                        }
                    }
                }
            }
        }
    }
}
