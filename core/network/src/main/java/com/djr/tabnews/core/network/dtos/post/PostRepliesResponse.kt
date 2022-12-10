package com.djr.tabnews.core.network.dtos.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostRepliesResponse(
    @SerialName("id")
    val id: String,

    @SerialName("owner_username")
    val ownerUsername: String,

    @SerialName("body")
    val body: String,

    @SerialName("tabcoins")
    val tabcoins: Int,

    @SerialName("children_deep_count")
    val repliesAmount: Int,

    @SerialName("children")
    val replies: List<PostRepliesResponse> = emptyList(),
)
