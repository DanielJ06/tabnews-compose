package com.djr.tabnews.core.domain.useCases.posts

import com.djr.tabnews.core.data.repository.posts.PostsRepository
import com.djr.tabnews.core.domain.Resource
import com.djr.tabnews.core.models.posts.PostReplies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostReplies @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(
        owner: String,
        slug: String
    ): Flow<Resource<List<PostReplies>>> = flow {
        try {
            emit(Resource.Loading())
            postsRepository.getPostReplies(owner, slug).collect {
                emit(Resource.Success(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}