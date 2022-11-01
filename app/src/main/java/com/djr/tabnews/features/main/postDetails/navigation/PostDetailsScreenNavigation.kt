package com.djr.tabnews.features.main.postDetails.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.djr.tabnews.features.main.postDetails.PostDetailsScreen

const val postDetailRoute = "post_detail_route"
const val postDetailIdArgs = "postId"

internal class PostDetailsArgs(val postId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        postId = Uri.decode(checkNotNull(savedStateHandle[postDetailIdArgs]))
    )
}

fun NavController.navigateToProfileDetails(postId: String) {
    val encodedId = Uri.encode(postId)
    this.navigate("$postDetailRoute/$encodedId")
}

fun NavGraphBuilder.addProfileDetailsScreen() {
    composable(
        route = "$postDetailRoute/{$postDetailIdArgs}",
        arguments = listOf(navArgument(postDetailIdArgs) { type = NavType.StringType })
    ) {
        PostDetailsScreen()
    }
}