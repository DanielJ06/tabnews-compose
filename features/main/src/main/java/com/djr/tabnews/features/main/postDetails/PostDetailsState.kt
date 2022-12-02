package com.djr.tabnews.features.main.postDetails

import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.models.posts.PostReplies

data class PostDetailsState(
    val isLoading: Boolean = false,
    val postDetail: PostContent? = null,
    val error: String = ""
)

data class PostRepliesState(
    val isLoading: Boolean = false,
    val postReplies: List<PostReplies> = emptyList(),
    val error: String = ""
)