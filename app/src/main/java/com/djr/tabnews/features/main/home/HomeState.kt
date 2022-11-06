package com.djr.tabnews.features.main.home

import com.djr.tabnews.domain.models.PostModel

data class HomeState(
    val isLoading: Boolean = false,
    val posts: List<PostModel> = emptyList(),
    val error: String = ""
)
