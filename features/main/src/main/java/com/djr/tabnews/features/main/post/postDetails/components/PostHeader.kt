package com.djr.tabnews.features.main.post.postDetails.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djr.tabnews.core.models.dummies.DUMMY_POST_CONTENT
import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.uikit.R
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@Composable
fun PostHeader(
    modifier: Modifier = Modifier,
    postContent: PostContent,
    isSaved: Boolean = false,
    onFollowClick: (author: String) -> Unit,
    onSaveClick: (post: PostContent) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "u/${postContent.ownerUsername}",
            style = TabNewsTheme.typography.TextNormal,
            color = TabNewsTheme.colors.accentPrimary.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = postContent.title,
            style = TabNewsTheme.typography.TextLargeSB,
            color = TabNewsTheme.colors.textNeutralLight,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Xxxs))
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            FollowAuthor(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                authorName = postContent.ownerUsername,
                authorId = postContent.ownerId,
                handleFollow = { _, _ -> })
            Spacer(modifier = Modifier.width(TabNewsTheme.spacing.Nano))
            BookmarkPost(postContent = postContent, onSaveClick = onSaveClick, isSaved = isSaved)
        }
    }
}

@Composable
fun FollowAuthor(
    modifier: Modifier = Modifier,
    authorName: String,
    authorId: String,
    alreadyFollows: Boolean = false,
    handleFollow: (authorName: String, authorId: String) -> Unit
) {
    var following by remember { mutableStateOf(alreadyFollows) }

    Button(
        modifier = modifier.height(IntrinsicSize.Min),
        colors = ButtonDefaults.buttonColors(backgroundColor = TabNewsTheme.colors.secondaryBg),
        shape = RoundedCornerShape(TabNewsTheme.spacing.Nano),
        border = BorderStroke(
            width = 1.dp,
            color = TabNewsTheme.colors.borderDark,
        ),
        onClick = {
            handleFollow(authorName, authorId)
            following = !following
        },
        contentPadding = PaddingValues(vertical = TabNewsTheme.spacing.Nano)
    ) {
        Icon(
            if (following) Icons.Default.Check else Icons.Default.Add,
            null,
            tint = TabNewsTheme.colors.textNeutralLight,
            modifier = Modifier.size(TabNewsTheme.spacing.Xxxs)
        )
        Text(
            text = if (following) "seguindo" else "seguir u/$authorName",
            style = TabNewsTheme.typography.TextNormalSB,
            color = TabNewsTheme.colors.textNeutralLight,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
        )
    }
}

@Composable
fun BookmarkPost(
    modifier: Modifier = Modifier,
    postContent: PostContent,
    isSaved: Boolean,
    onSaveClick: (post: PostContent) -> Unit
) {
    var saved by remember { mutableStateOf(isSaved) }

    LaunchedEffect(isSaved) {
        saved = isSaved
    }

    Button(
        modifier = modifier.height(IntrinsicSize.Min),
        colors = ButtonDefaults.buttonColors(backgroundColor = TabNewsTheme.colors.secondaryBg),
        shape = RoundedCornerShape(TabNewsTheme.spacing.Nano),
        border = BorderStroke(
            width = 1.dp,
            color = TabNewsTheme.colors.borderDark,
        ),
        onClick = {
            onSaveClick.invoke(postContent)
            saved = !saved
        },
        contentPadding = PaddingValues(vertical = TabNewsTheme.spacing.Nano),
    ) {
        Icon(
            painterResource(if (saved) R.drawable.bookmarked else R.drawable.bookmark),
            null,
            tint = TabNewsTheme.colors.textNeutralLight,
            modifier = Modifier.size(TabNewsTheme.spacing.Xxxs)
        )
    }
}

@Preview
@Composable
fun PostHeaderPreview() {
    PostHeader(postContent = DUMMY_POST_CONTENT, onFollowClick = {}, onSaveClick = {})
}
