package com.djr.tabnews.core.network.dataSource

import com.djr.tabnews.core.models.posts.PostModel
import com.djr.tabnews.core.network.TabNewsService
import com.djr.tabnews.core.network.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsNetworkDtsImpl @Inject constructor(
    private val tabNewsService: TabNewsService
) : PostsNetworkDts {

    override suspend fun getPosts(): Flow<List<PostModel>> = flow {
        emit(tabNewsService.getPosts().map { it.toModel() })
    }

}