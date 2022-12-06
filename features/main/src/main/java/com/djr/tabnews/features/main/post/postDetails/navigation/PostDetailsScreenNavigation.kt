package com.djr.tabnews.features.main.post.postDetails.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.djr.tabnews.core.models.posts.PostThread
import com.djr.tabnews.features.main.post.postDetails.PostDetailsRoute

const val postDetailRoutePattern = "post_detail_route/{owner}/{slug}"
const val postDetailRoute = "post_detail_route"
const val ownerArgName = "owner"
const val slugArgName = "slug"

internal class PostDetailsArgs(
    val owner: String,
    val slug: String
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        owner = Uri.decode(checkNotNull(savedStateHandle[ownerArgName])),
        slug = Uri.decode(checkNotNull(savedStateHandle[slugArgName])),
    )
}

fun NavController.navigateToPostDetails(owner: String, slug: String) {
    val encodedOwner = Uri.encode(owner)
    val encodedSlug = Uri.encode(slug)
    this.navigate("$postDetailRoute/$encodedOwner/$encodedSlug")
}

fun NavGraphBuilder.addPostDetailsScreen(
    navigateToReplyChildren: (PostThread) -> Unit
) {
    composable(
        route = "$postDetailRoute/{$ownerArgName}/{$slugArgName}",
        arguments = listOf(
            navArgument(ownerArgName) { type = NavType.StringType },
            navArgument(slugArgName) { type = NavType.StringType }
        )
    ) {
        PostDetailsRoute(navigateToReplyChildren = navigateToReplyChildren)
    }
}