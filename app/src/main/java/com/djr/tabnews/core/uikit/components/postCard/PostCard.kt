package com.djr.tabnews.core.uikit.components.postCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
            .padding(TabNewsTheme.spacing.Xxxs)
            .height(75.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .quarkCorners()
                .background(TabNewsTheme.colors.tabMoney.copy(alpha = 0.2f))
                .padding(
                    horizontal = TabNewsTheme.spacing.Nano,
                    vertical = TabNewsTheme.spacing.Quark
                )
        ) {
            Text(
                text = postItem.tabcoins.toString(),
                color = TabNewsTheme.colors.tabMoney,
                fontWeight = FontWeight.Bold,
                style = TabNewsTheme.typography.TextNormalSB
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
                text = "${postItem.ownerUsername.toCapitalized()} - ${postItem.commentsAmount} comments",
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
    val dummyPost = PostModel(
        id = "54ba5af4-0ac6-4880-9f94-c635af938276",
        ownerId = "54ba5af4-0ac6-4880-9f94-c635af938276",
        title = "Quais foram os maiores obstáculos que vocês já passaram como programadores(as)?",
        publishedAt = "2022-10-28T22:23:02.042Z",
        ownerUsername = "danielj06",
        tabcoins = 3,
        commentsAmount = 32
    )
    PostCard(postItem = dummyPost)
}