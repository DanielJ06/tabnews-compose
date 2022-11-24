package com.djr.tabnews.core.network.dataSource

import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.models.posts.PostModel
import kotlinx.coroutines.flow.Flow

interface PostsNetworkDts {
    suspend fun getPosts(): Flow<List<PostModel>>
    suspend fun getPostDetail(owner: String, slug: String): Flow<PostContent>
}