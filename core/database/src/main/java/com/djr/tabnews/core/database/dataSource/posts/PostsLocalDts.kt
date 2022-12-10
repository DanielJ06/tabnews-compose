package com.djr.tabnews.core.database.dataSource.posts

import com.djr.tabnews.core.models.posts.OfflinePostModel
import com.djr.tabnews.core.models.tags.TagModel
import kotlinx.coroutines.flow.Flow

interface PostsLocalDts {
    suspend fun isPostSaved(postId: String): Flow<Boolean>
    suspend fun getAllPosts(): Flow<List<OfflinePostModel>>
    suspend fun getAllTags(): Flow<List<TagModel>>

    suspend fun toggleSavePost(post: OfflinePostModel, tags: List<TagModel>): Flow<Unit>
}