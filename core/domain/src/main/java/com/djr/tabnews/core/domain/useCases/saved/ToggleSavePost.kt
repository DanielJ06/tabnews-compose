package com.djr.tabnews.core.domain.useCases.saved

import com.djr.tabnews.core.data.repository.posts.PostsRepository
import com.djr.tabnews.core.domain.Resource
import com.djr.tabnews.core.models.posts.OfflinePostModel
import com.djr.tabnews.core.models.tags.TagModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToggleSavePost @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(
        post: OfflinePostModel,
        tags: List<TagModel>
    ): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            postsRepository.toggleSavePost(post, tags).collect {
                emit(Resource.Success(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong!"))
        }
    }
}