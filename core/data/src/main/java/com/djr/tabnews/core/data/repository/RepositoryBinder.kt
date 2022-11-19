package com.djr.tabnews.core.data.repository

import com.djr.tabnews.core.data.repository.posts.PostsRepository
import com.djr.tabnews.core.data.repository.posts.PostsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBinder {
    @Binds
    fun bindsPostsRepository(
        postsRepositoryImpl: PostsRepositoryImpl
    ): PostsRepository
}