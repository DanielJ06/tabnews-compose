package com.djr.tabnews.features.main.post.replyChild

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.djr.tabnews.core.models.dummies.DUMMY_THREAD
import com.djr.tabnews.core.models.posts.PostThread
import com.djr.tabnews.core.uikit.components.reply.BaseReply
import com.djr.tabnews.core.uikit.components.reply.ChildReply
import com.djr.tabnews.core.uikit.components.reply.ReplyActions
import com.djr.tabnews.core.uikit.components.reply.ReplyActionsBar
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ReplyChildrenRoute(
    viewModel: ReplyChildrenViewModel = hiltViewModel(),
    navigateToNestedChildren: (PostThread) -> Unit
) {
    val replyChildrenState by viewModel.replyChildrenState.collectAsStateWithLifecycle()
    ReplyChildrenScreen(replyChildrenState, navigateToNestedChildren)
}

@Composable
fun ReplyChildrenScreen(
    state: ReplyChildrenState,
    navigateToNestedChildren: (PostThread) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(TabNewsTheme.colors.primaryBg)
    ) {
        state.thread?.let { thread ->
            item(key = thread.topReply.id) {
                Column {
                    Column(
                        modifier = Modifier.padding(
                            horizontal = TabNewsTheme.spacing.Xxxs,
                            vertical = TabNewsTheme.spacing.Mini,
                        )
                    ) {
                        BaseReply(postReplies = thread.topReply)
                    }
                    ReplyActionsBar(
                        tabcoinsAmount = thread.topReply.tabcoins,
                        repliesAmount = thread.topReply.replies.size
                    )
                    Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
                }
            }
            itemsIndexed(
                items = thread.children,
            ) { index, child ->
                if (index == 0) Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
                Column(
                    modifier = Modifier.padding(horizontal = TabNewsTheme.spacing.Xxxs)
                ) {
                    Column {
                        BaseReply(postReplies = child)
                        ReplyActions(replies = child, seeMore = false)
                    }
                    child.replies.forEachIndexed { i, post ->
                        Column(Modifier.padding(horizontal = TabNewsTheme.spacing.Xxs)) {
                            if (i == 0) Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
                            ChildReply(
                                postReplies = post,
                                seeMore = true,
                                seeMoreCb = navigateToNestedChildren
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
                }
            }
        }
    }
}

@Preview
@Composable
fun ReplyChildrenPreview() {
    ReplyChildrenScreen(
        ReplyChildrenState(thread = DUMMY_THREAD)
    ) {}
}