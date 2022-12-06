package com.djr.tabnews.core.domain.useCases.posts

import com.djr.tabnews.core.data.repository.posts.PostsRepository
import com.djr.tabnews.core.domain.Resource
import com.djr.tabnews.core.models.posts.PostContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostDetail @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(
        owner: String,
        slug: String
    ): Flow<Resource<PostContent>> = flow {
        try {
            emit(Resource.Loading())
            postsRepository.getPostDetail(owner, slug).collect {
                emit(Resource.Success(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong!"))
        }
    }
}