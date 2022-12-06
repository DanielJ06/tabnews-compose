package com.djr.tabnews.core.network.dtos.post

import com.google.gson.annotations.SerializedName

data class PostRepliesResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("owner_username")
    val ownerUsername: String,

    @SerializedName("body")
    val body: String,

    @SerializedName("tabcoins")
    val tabcoins: Int,

    @SerializedName("children_deep_count")
    val repliesAmount: Int,

    @SerializedName("children")
    val replies: List<PostRepliesResponse> = emptyList(),
)
