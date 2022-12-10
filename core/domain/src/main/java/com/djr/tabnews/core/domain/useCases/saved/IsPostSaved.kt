package com.djr.tabnews.core.domain.useCases.saved

import com.djr.tabnews.core.data.repository.posts.PostsRepository
import com.djr.tabnews.core.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsPostSaved @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(
        postId: String
    ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            postsRepository.isPostSaved(postId).collect {
                emit(Resource.Success(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong!"))
        }
    }
}