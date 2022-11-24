package com.djr.tabnews.core.models.posts

data class PostContent(
    val id: String,
    val ownerId: String,
    val ownerUsername: String,
    val slug: String,
    val title: String,
    val body: String,
    val publishedAt: String,
    val tabcoins: Int,
    val comments: Int
)
