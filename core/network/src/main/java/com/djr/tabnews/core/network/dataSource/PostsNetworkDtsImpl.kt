package com.djr.tabnews.core.network.dataSource

import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.models.posts.PostModel
import com.djr.tabnews.core.models.posts.PostReplies
import com.djr.tabnews.core.network.TabNewsService
import com.djr.tabnews.core.network.dtos.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsNetworkDtsImpl @Inject constructor(
    private val tabNewsService: TabNewsService
) : PostsNetworkDts {

    override suspend fun getPosts(): Flow<List<PostModel>> = flow {
        emit(tabNewsService.getPosts().map { it.toModel() })
    }

    override suspend fun getPostDetail(owner: String, slug: String): Flow<PostContent> = flow {
        emit(tabNewsService.getPostDetail(owner, slug).toModel())
    }

    override suspend fun getPostReplies(owner: String, slug: String): Flow<List<PostReplies>> = flow {
        emit(tabNewsService.getPostReplies(owner, slug).map { reply -> reply.toModel() })
    }

}