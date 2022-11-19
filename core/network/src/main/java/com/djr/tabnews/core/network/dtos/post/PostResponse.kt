package com.djr.tabnews.core.network.dtos.post

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("owner_id")
    val ownerId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("owner_username")
    val ownerUsername: String,

    @SerializedName("published_at")
    val publishedAt: String,

    @SerializedName("tabcoins")
    val tabcoins: Int,

    @SerializedName("children_deep_count")
    val commentsAmount: Int
)
