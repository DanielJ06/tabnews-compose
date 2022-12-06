package com.djr.tabnews.core.models.posts

data class PostModel(
    val id: String,
    val ownerId: String,
    val slug: String,
    val title: String,
    val ownerUsername: String,
    val publishedAt: String,
    val tabcoins: Int,
    val commentsAmount: Int
)
