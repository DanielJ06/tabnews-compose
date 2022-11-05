package com.djr.tabnews.domain.models

data class PostModel(
    val id: String,
    val ownerId: String,
    val title: String,
    val ownerUsername: String,
    val publishedAt: String,
    val tabcoins: Int,
    val commentsAmount: Int
)
