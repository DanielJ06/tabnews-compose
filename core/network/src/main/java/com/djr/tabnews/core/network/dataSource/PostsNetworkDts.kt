package com.djr.tabnews.core.network.dataSource

import com.djr.tabnews.core.models.posts.PostModel
import kotlinx.coroutines.flow.Flow

interface PostsNetworkDts {
    suspend fun getPosts(): Flow<List<PostModel>>
}