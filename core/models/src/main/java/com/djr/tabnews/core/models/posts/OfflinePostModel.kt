package com.djr.tabnews.core.models.posts

data class OfflinePostModel(
    val id: String,
    val ownerId: String,
    val slug: String,
    val title: String,
    val ownerUsername: String,
    val createdAt: Long = System.currentTimeMillis()
)

fun PostContent.toOffline() = OfflinePostModel(
    id = id,
    ownerId = ownerId,
    slug = slug,
    title = title,
    ownerUsername = ownerUsername
)