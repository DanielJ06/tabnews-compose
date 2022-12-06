package com.djr.tabnews.core.models.posts

data class PostThread(
    val topReply: PostReplies,
    val children: List<PostReplies>
)
