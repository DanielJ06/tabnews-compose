package com.djr.tabnews.core.network.dtos

import com.djr.tabnews.core.models.auth.LoginModel
import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.models.posts.PostModel
import com.djr.tabnews.core.models.posts.PostReplies
import com.djr.tabnews.core.network.dtos.auth.LoginRequest
import com.djr.tabnews.core.network.dtos.post.PostContentResponse
import com.djr.tabnews.core.network.dtos.post.PostRepliesResponse
import com.djr.tabnews.core.network.dtos.post.PostResponse

fun LoginModel.toRequest() = LoginRequest(
    email = email,
    password = password
)

fun PostResponse.toModel() = PostModel(
    id = id,
    title = title,
    slug = slug,
    ownerId = ownerId,
    ownerUsername = ownerUsername,
    publishedAt = publishedAt,
    tabcoins = tabcoins,
    commentsAmount = commentsAmount
)

fun PostContentResponse.toModel() = PostContent(
    id = id,
    ownerId = ownerId,
    ownerUsername = ownerUsername,
    slug = slug,
    title = title,
    body = body,
    publishedAt = publishedAt,
    tabcoins = tabcoins,
    comments = comments
)

fun PostRepliesResponse.toModel() = PostReplies(
    id = id,
    ownerUsername = ownerUsername,
    body = body,
    tabcoins = tabcoins,
    repliesAmount = repliesAmount,
    replies = replies.toModel()
)

fun List<PostRepliesResponse>.toModel(): List<PostReplies> {
    return this.map {
        PostReplies(
            id = it.id,
            ownerUsername = it.ownerUsername,
            body = it.body,
            tabcoins = it.tabcoins,
            repliesAmount = it.repliesAmount,
            replies = it.replies.toModel()
        )
    }
}