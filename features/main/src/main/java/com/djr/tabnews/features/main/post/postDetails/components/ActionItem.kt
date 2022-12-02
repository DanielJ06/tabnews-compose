package com.djr.tabnews.features.main.post.postDetails.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@Composable
fun ActionItem(
    modifier: Modifier = Modifier,
    info: String,
    @DrawableRes icon: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        Icon(
            modifier = Modifier.size(TabNewsTheme.spacing.Xxxs),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = TabNewsTheme.colors.textNeutralLight,
        )
        Spacer(modifier = Modifier.width(TabNewsTheme.spacing.Nano))
        Text(
            text = info,
            style = TabNewsTheme.typography.TextNormalSB,
            color = TabNewsTheme.colors.textNeutralLight,
            maxLines = 1,
        )
    }
}