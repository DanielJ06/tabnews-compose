package com.djr.tabnews.data.repository

import com.djr.tabnews.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBinder {
    @Binds
    abstract fun bindsPostsRepository(
        postsRepositoryImpl: PostsRepositoryImpl
    ): PostsRepository
}