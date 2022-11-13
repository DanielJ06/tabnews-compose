package com.djr.tabnews.repository

import com.djr.tabnews.core.models.posts.PostModel
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPosts(): Flow<List<PostModel>>
}