package com.djr.tabnews.core.data.repository.posts

import com.djr.tabnews.core.models.posts.PostModel
import com.djr.tabnews.core.network.dataSource.PostsNetworkDts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsNetworkDts: PostsNetworkDts
) : PostsRepository {
    override suspend fun getPosts(): Flow<List<PostModel>> {
        return postsNetworkDts.getPosts()
    }
}