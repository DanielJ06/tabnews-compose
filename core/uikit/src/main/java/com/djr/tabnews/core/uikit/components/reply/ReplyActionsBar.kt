package com.djr.tabnews.core.uikit.components.reply

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
import com.djr.tabnews.core.uikit.R
import com.djr.tabnews.core.uikit.components.common.ActionItem
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.djr.tabnews.core.uikit.theme.extensions.prettyCount

@Composable
fun ReplyActionsBar(
    modifier: Modifier = Modifier,
    tabcoinsAmount: Int,
    upVoteCb: () -> Unit = {},
    downVoteCb: () -> Unit = {},
    repliesAmount: Int,
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
        ActionItem(
            info = tabcoinsAmount.prettyCount(),
            icon = R.drawable.thumbs_up,
            onActionClick = upVoteCb
        )
        ActionItem(info = "", icon = R.drawable.thumbs_down, onActionClick = downVoteCb)
        ActionItem(info = repliesAmount.toString(), icon = R.drawable.comment)
        ActionItem(info = "", icon = R.drawable.share)
    }
}

@Preview
@Composable
fun PostActionsPreview() {
    ReplyActionsBar(tabcoinsAmount = 12, repliesAmount = 23)
}