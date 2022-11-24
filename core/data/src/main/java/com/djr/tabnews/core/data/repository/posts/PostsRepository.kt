package com.djr.tabnews.core.data.repository.posts

import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.models.posts.PostModel
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPosts(): Flow<List<PostModel>>
    suspend fun getPostDetail(owner: String, slug: String): Flow<PostContent>
}