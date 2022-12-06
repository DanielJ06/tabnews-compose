package com.djr.tabnews.core.network.dtos.post

import com.google.gson.annotations.SerializedName

data class PostContentResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("owner_id")
    val ownerId: String,

    @SerializedName("owner_username")
    val ownerUsername: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String,

    @SerializedName("published_at")
    val publishedAt: String,

    @SerializedName("tabcoins")
    val tabcoins: Int,

    @SerializedName("children_deep_count")
    val comments: Int
)
