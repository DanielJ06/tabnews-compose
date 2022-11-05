package com.djr.tabnews.data.dataSource

import com.djr.tabnews.domain.models.PostModel
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {
    suspend fun getPosts(): Flow<List<PostModel>>
}