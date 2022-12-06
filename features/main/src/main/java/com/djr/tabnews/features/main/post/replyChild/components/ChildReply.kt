package com.djr.tabnews.features.main.post.replyChild.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djr.tabnews.core.models.dummies.DUMMY_POST_REPLY
import com.djr.tabnews.core.models.posts.PostReplies
import com.djr.tabnews.core.models.posts.PostThread
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@Composable
fun ChildReply(
    modifier: Modifier = Modifier,
    postReplies: PostReplies,
    seeMore: Boolean = false,
    seeMoreCb: (PostThread) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(3.dp)
                .background(TabNewsTheme.colors.accentPrimary)
        )
        Spacer(modifier = Modifier.width(TabNewsTheme.spacing.Xxxs))
        Column(
            modifier = Modifier.padding(vertical = TabNewsTheme.spacing.Nano)
        ) {
            BaseReply(postReplies = postReplies, seeMore = seeMore, seeMoreCb = seeMoreCb)
        }
    }
}

@Preview
@Composable
fun ChildReplyPreview() {
    ChildReply(postReplies = DUMMY_POST_REPLY)
}