package com.djr.tabnews.features.main.post.postDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djr.tabnews.core.models.dummies.DUMMY_POST_REPLY
import com.djr.tabnews.core.models.posts.PostReplies
import com.djr.tabnews.core.uikit.R
import com.djr.tabnews.core.uikit.components.markdownWrapper.Markdown
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.djr.tabnews.core.uikit.theme.extensions.nanoCorners

@Composable
fun PostReply(
    modifier: Modifier = Modifier,
    postReplies: PostReplies,
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
        Text(
            text = "u/${postReplies.ownerUsername}",
            style = TabNewsTheme.typography.TextNormal,
            color = TabNewsTheme.colors.accentPrimary,
            fontWeight = FontWeight.Bold,
        )
        ReplyContent(body = postReplies.body)
        Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
        ReplyActions(replies = postReplies)
    }
}

@Composable
fun ReplyContent(
    body: String
) {
    Column {
        Markdown(
            markdown = body,
            style = TabNewsTheme.typography.TextSmall,
            color = TabNewsTheme.colors.textNeutralLight,
        )
    }
}

@Composable
fun ReplyActions(
    modifier: Modifier = Modifier,
    replies: PostReplies
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Row {
            ActionItem(info = replies.tabcoins.toString(), icon = R.drawable.thumbs_up)
            Spacer(modifier = Modifier.width(TabNewsTheme.spacing.Mini))
            ActionItem(info = "", icon = R.drawable.thumbs_down)
        }
        ActionItem(info = "Responder", icon = R.drawable.reply)
    }
    if (replies.repliesAmount > 0) {
        Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
        Text(
            text = "Ver ${replies.repliesAmount} respostas",
            color = TabNewsTheme.colors.accentPrimary,
            fontWeight = FontWeight.Bold,
            style = TabNewsTheme.typography.TextSmallSB,
        )
    }
}

@Preview
@Composable
fun PostReplyPreview() {
    PostReply(postReplies = DUMMY_POST_REPLY)
}