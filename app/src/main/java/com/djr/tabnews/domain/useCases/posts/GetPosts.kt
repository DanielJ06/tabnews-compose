package com.djr.tabnews.domain.useCases.posts

import com.djr.tabnews.domain.Resource
import com.djr.tabnews.domain.models.PostModel
import com.djr.tabnews.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<PostModel>>> = flow {
        try {
            emit(Resource.Loading())
            postsRepository.getPosts().collect {
                emit(Resource.Success(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong!"))
        }
    }
}