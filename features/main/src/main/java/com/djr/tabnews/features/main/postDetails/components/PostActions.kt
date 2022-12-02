package com.djr.tabnews.features.main.postDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djr.tabnews.core.models.dummies.DUMMY_POST_CONTENT
import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.uikit.R
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@Composable
fun PostActions(
    modifier: Modifier = Modifier,
    postContent: PostContent
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .background(TabNewsTheme.colors.secondaryBg)
            .border(
                width = 1.dp,
                color = TabNewsTheme.colors.borderDark,
            )
            .padding(vertical = TabNewsTheme.spacing.Mini)
    ) {
        ActionItem(info = postContent.tabcoins.toString(), icon = R.drawable.thumbs_up)
        ActionItem(info = "", icon = R.drawable.thumbs_down)
        ActionItem(info = postContent.comments.toString(), icon = R.drawable.comment)
        ActionItem(info = "", icon = R.drawable.share)
    }
}

@Preview
@Composable
fun PostActionsPreview() {
    PostActions(postContent = DUMMY_POST_CONTENT)
}