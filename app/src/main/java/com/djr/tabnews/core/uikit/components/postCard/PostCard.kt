package com.djr.tabnews.core.uikit.components.postCard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djr.tabnews.core.extensions.Constants
import com.djr.tabnews.core.extensions.formatDate
import com.djr.tabnews.core.extensions.toCapitalized
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.djr.tabnews.core.uikit.theme.extensions.nanoCorners
import com.djr.tabnews.core.uikit.theme.extensions.quarkCorners
import com.djr.tabnews.domain.models.PostModel
import com.djr.tabnews.domain.models.dummies.DUMMY_POST

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    postItem: PostModel
) {
    Row(
        modifier
            .fillMaxWidth()
            .nanoCorners()
            .background(TabNewsTheme.colors.secondaryBg)
            .border(
                width = 1.dp,
                color = TabNewsTheme.colors.borderDark,
                shape = RoundedCornerShape(TabNewsTheme.spacing.Nano)
            )
            .padding(TabNewsTheme.spacing.Xxxs)
            .height(75.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .quarkCorners()
                .background(TabNewsTheme.colors.accentPrimary.copy(alpha = 0.2f))
                .padding(
                    horizontal = TabNewsTheme.spacing.Nano,
                    vertical = TabNewsTheme.spacing.Quark
                )
        ) {
            Text(
                text = postItem.tabcoins.toString(),
                color = TabNewsTheme.colors.accentPrimary,
                fontWeight = FontWeight.Bold,
                style = TabNewsTheme.typography.TextSmallSB
            )
        }
        Spacer(modifier = Modifier.width(TabNewsTheme.spacing.Xxxs))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = postItem.title.toCapitalized(),
                color = TabNewsTheme.colors.textNeutralLight,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TabNewsTheme.typography.TextNormalSB
            )
            Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Quark))
            Text(
                text = "${postItem.ownerUsername} - ${postItem.commentsAmount} comments",
                color = TabNewsTheme.colors.textNeutral,
                fontWeight = FontWeight.SemiBold,
                style = TabNewsTheme.typography.TextSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(TabNewsTheme.spacing.Xxxs))
        Text(
            text = postItem.publishedAt.formatDate(to = Constants.USUAL_DATE),
            color = TabNewsTheme.colors.textNeutralLight,
            style = TabNewsTheme.typography.TextSmallerSB
        )
    }
}

@Preview
@Composable
fun PostCardPreview() {
    PostCard(postItem = DUMMY_POST)
}