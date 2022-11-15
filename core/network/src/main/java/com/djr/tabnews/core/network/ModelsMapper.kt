package com.djr.tabnews.core.network

import com.djr.tabnews.core.models.posts.PostModel
import com.djr.tabnews.core.network.dtos.post.PostResponse

fun PostResponse.toModel() = PostModel(
    id = id,
    title = title,
    ownerId = ownerId,
    ownerUsername = ownerUsername,
    publishedAt = publishedAt,
    tabcoins = tabcoins,
    commentsAmount = commentsAmount
)