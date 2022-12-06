package com.djr.tabnews.features.main.post.replyChild.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.djr.tabnews.core.models.dummies.DUMMY_POST_REPLY
import com.djr.tabnews.core.models.posts.PostReplies
import com.djr.tabnews.core.models.posts.PostThread
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.djr.tabnews.features.main.post.postDetails.components.ReplyActions
import com.djr.tabnews.features.main.post.postDetails.components.ReplyContent

@Composable
fun BaseReply(
    modifier: Modifier = Modifier,
    postReplies: PostReplies,
    seeMore: Boolean = false,
    seeMoreCb: (PostThread) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "u/${postReplies.ownerUsername}",
            style = TabNewsTheme.typography.TextNormal,
            color = TabNewsTheme.colors.accentPrimary,
            fontWeight = FontWeight.Bold,
        )
        ReplyContent(body = postReplies.body)
        Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
        ReplyActions(replies = postReplies, seeMore = seeMore, seeMoreCb = seeMoreCb)
    }
}

@Preview
@Composable
fun BaseReplyPreview() {
    BaseReply(postReplies = DUMMY_POST_REPLY)
}