package com.djr.tabnews.data.network.dataSource

import com.djr.tabnews.data.dataSource.NetworkDataSource
import com.djr.tabnews.data.network.TabNewsService
import com.djr.tabnews.data.toModel
import com.djr.tabnews.core.models.posts.PostModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val tabNewsService: TabNewsService
) : NetworkDataSource {

    override suspend fun getPosts(): Flow<List<PostModel>> = flow {
        emit(tabNewsService.getPosts().map { it.toModel() })
    }

}