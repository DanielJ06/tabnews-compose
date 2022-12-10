package com.djr.tabnews.features.main.post.replyChild.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.djr.tabnews.core.models.posts.PostThread
import com.djr.tabnews.features.main.post.replyChild.ReplyChildrenRoute
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val replyChildrenPattern = "reply_children/{thread}"
const val replyChildrenRoute = "reply_children"
const val threadArgName = "thread"

internal class ReplyChildrenArgs(
    val thread: PostThread
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        thread = Json.decodeFromString<PostThread>(
            Uri.decode(checkNotNull(savedStateHandle[threadArgName]))
        )
    )
}

fun NavController.navigateToReplyChildrenScreen(thread: PostThread) {
    val encodedThread = Uri.encode(Json.encodeToString(thread))
    this.navigate("$replyChildrenRoute/$encodedThread")
}

fun NavGraphBuilder.addReplyChildrenScreen(
    navigateToNestedChildren: (PostThread) -> Unit
) {
    composable(
        route = "$replyChildrenRoute/{$threadArgName}",
        arguments = listOf(

        )
    ) {
        ReplyChildrenRoute(navigateToNestedChildren = navigateToNestedChildren)
    }
}