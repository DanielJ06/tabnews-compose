package com.djr.tabnews.core.data.repository.posts

import com.djr.tabnews.core.database.dataSource.posts.PostsLocalDts
import com.djr.tabnews.core.models.posts.OfflinePostModel
import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.models.posts.PostModel
import com.djr.tabnews.core.models.posts.PostReplies
import com.djr.tabnews.core.models.tags.TagModel
import com.djr.tabnews.core.network.dataSource.PostsNetworkDts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsNetworkDts: PostsNetworkDts,
    private val postsLocalDts: PostsLocalDts
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

    override suspend fun isPostSaved(postId: String): Flow<Boolean> {
        return postsLocalDts.isPostSaved(postId)
    }

    override suspend fun toggleSavePost(
        postContent: OfflinePostModel,
        tags: List<TagModel>
    ): Flow<Unit> {
        return postsLocalDts.toggleSavePost(postContent, tags)
    }
}