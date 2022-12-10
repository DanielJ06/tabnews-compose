package com.djr.tabnews.core.models.posts

import kotlinx.serialization.Serializable

@Serializable
data class PostReplies(
    val id: String,
    val ownerUsername: String,
    val body: String,
    val tabcoins: Int,
    val repliesAmount: Int,
    val replies: List<PostReplies> = emptyList(),
)