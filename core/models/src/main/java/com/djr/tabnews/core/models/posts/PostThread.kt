package com.djr.tabnews.core.models.posts

import kotlinx.serialization.Serializable

@Serializable
data class PostThread(
    val topReply: PostReplies,
    val children: List<PostReplies>
)
