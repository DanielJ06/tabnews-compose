package com.djr.tabnews.domain.repository

import com.djr.tabnews.domain.models.PostModel
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPosts(): Flow<List<PostModel>>
}