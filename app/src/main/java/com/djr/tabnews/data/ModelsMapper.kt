package com.djr.tabnews.data

import com.djr.tabnews.data.network.dtos.post.PostResponse
import com.djr.tabnews.domain.models.PostModel

fun PostResponse.toModel() = PostModel(
    id = id,
    title = title,
    ownerId = ownerId,
    ownerUsername = ownerUsername,
    publishedAt = publishedAt,
    tabcoins = tabcoins,
    commentsAmount = commentsAmount
)