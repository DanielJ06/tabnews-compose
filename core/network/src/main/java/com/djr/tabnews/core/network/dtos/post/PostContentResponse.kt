package com.djr.tabnews.core.network.dtos.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostContentResponse(
    @SerialName("id")
    val id: String,

    @SerialName("owner_id")
    val ownerId: String,

    @SerialName("owner_username")
    val ownerUsername: String,

    @SerialName("slug")
    val slug: String,

    @SerialName("title")
    val title: String,

    @SerialName("body")
    val body: String,

    @SerialName("published_at")
    val publishedAt: String,

    @SerialName("tabcoins")
    val tabcoins: Int,

    @SerialName("children_deep_count")
    val comments: Int
)
