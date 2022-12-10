package com.djr.tabnews.core.uikit.components.reply

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djr.tabnews.core.models.dummies.DUMMY_POST_REPLY
import com.djr.tabnews.core.models.posts.PostReplies
import com.djr.tabnews.core.models.posts.PostThread
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.djr.tabnews.core.uikit.theme.extensions.nanoCorners

@Composable
fun BgReply(
    modifier: Modifier = Modifier,
    postReplies: PostReplies,
    seeMore: Boolean = false,
    seeMoreCb: (PostThread) -> Unit = {}
) {
    Column(
        modifier = modifier
            .nanoCorners()
            .background(TabNewsTheme.colors.secondaryBg)
            .border(
                width = 1.dp,
                color = TabNewsTheme.colors.borderDark,
                shape = RoundedCornerShape(TabNewsTheme.spacing.Nano)
            )
            .padding(TabNewsTheme.spacing.Mini)
    ) {
        BaseReply(postReplies = postReplies)
        ReplyActions(replies = postReplies, seeMore = seeMore, seeMoreCb = seeMoreCb)
    }
}

@Preview
@Composable
fun PostReplyPreview() {
    BgReply(postReplies = DUMMY_POST_REPLY)
}
