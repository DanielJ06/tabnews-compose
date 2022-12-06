package com.djr.tabnews.core.data.repository.posts

import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.models.posts.PostModel
import com.djr.tabnews.core.models.posts.PostReplies
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPosts(): Flow<List<PostModel>>
    suspend fun getPostDetail(owner: String, slug: String): Flow<PostContent>
    suspend fun getPostReplies(owner: String, slug: String): Flow<List<PostReplies>>
}