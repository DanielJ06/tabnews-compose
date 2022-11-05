package com.djr.tabnews.data.repository

import com.djr.tabnews.data.dataSource.NetworkDataSource
import com.djr.tabnews.domain.models.PostModel
import com.djr.tabnews.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : PostsRepository {
    override suspend fun getPosts(): Flow<List<PostModel>> {
        return networkDataSource.getPosts()
    }
}