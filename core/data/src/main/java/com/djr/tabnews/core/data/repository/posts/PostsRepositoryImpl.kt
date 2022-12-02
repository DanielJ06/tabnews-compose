package com.djr.tabnews.core.data.repository.posts

import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.models.posts.PostModel
import com.djr.tabnews.core.models.posts.PostReplies
import com.djr.tabnews.core.network.dataSource.PostsNetworkDts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsNetworkDts: PostsNetworkDts
) : PostsRepository {
    override suspend fun getPosts(): Flow<List<PostModel>> {
        return postsNetworkDts.getPosts()
    }

    override suspend fun getPostDetail(owner: String, slug: String): Flow<PostContent> {
        return postsNetworkDts.getPostDetail(owner, slug)
    }

    override suspend fun getPostReplies(owner: String, slug: String): Flow<List<PostReplies>> {
        return postsNetworkDts.getPostReplies(owner, slug)
    }
}