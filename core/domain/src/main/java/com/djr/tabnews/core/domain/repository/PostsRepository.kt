package com.djr.tabnews.core.domain.repository

import com.djr.tabnews.core.models.posts.PostModel
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPosts(): Flow<List<PostModel>>
}