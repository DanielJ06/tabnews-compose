package com.djr.tabnews.core.network.dtos

import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.models.posts.PostModel
import com.djr.tabnews.core.network.dtos.post.PostContentResponse
import com.djr.tabnews.core.network.dtos.post.PostResponse

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