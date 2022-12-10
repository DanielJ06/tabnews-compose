package com.djr.tabnews.core.database.dataSource.posts

import com.djr.tabnews.core.database.daos.PostsDao
import com.djr.tabnews.core.database.daos.TagsDao
import com.djr.tabnews.core.database.entities.OfflinePostEntity
import com.djr.tabnews.core.database.entities.PostTagCrossRef
import com.djr.tabnews.core.database.entities.TagEntity
import com.djr.tabnews.core.database.toEntity
import com.djr.tabnews.core.database.toModel
import com.djr.tabnews.core.models.posts.OfflinePostModel
import com.djr.tabnews.core.models.tags.TagModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostsLocalDtsImpl @Inject constructor(
    private val postsDao: PostsDao,
    private val tagsDao: TagsDao
) : PostsLocalDts {
    override suspend fun isPostSaved(postId: String) = flow {
        emit(postsDao.isPostSaved(postId))
    }

    override suspend fun getAllPosts(): Flow<List<OfflinePostModel>> {
        return postsDao.getSavedPosts().map { it.map(OfflinePostEntity::toModel) }
    }

    override suspend fun getAllTags(): Flow<List<TagModel>> {
        return tagsDao.getTags().map { it.map(TagEntity::toModel) }
    }

    override suspend fun toggleSavePost(post: OfflinePostModel, tags: List<TagModel>): Flow<Unit> {
        val isSaved = postsDao.isPostSaved(post.id)
        return if (isSaved) {
            removeSavedPost(post.id)
        } else {
            savePost(post, tags)
        }
    }

    private suspend fun savePost(post: OfflinePostModel, tags: List<TagModel>) = flow {
        postsDao.savePost(post.toEntity())
        tagsDao.saveTags(tags.map { it.toEntity() })
        tags.forEach {
            postsDao.saveOrIgnoreCrossRef(
                PostTagCrossRef(
                    postId = post.id,
                    tagName = it.tagName
                )
            )
        }
        emit(Unit)
    }

    private suspend fun removeSavedPost(postId: String): Flow<Unit> = flow {
        postsDao.deletePost(postId)
    }
}