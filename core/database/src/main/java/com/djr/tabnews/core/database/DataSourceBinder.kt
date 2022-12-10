package com.djr.tabnews.core.database

import com.djr.tabnews.core.database.dataSource.posts.PostsLocalDts
import com.djr.tabnews.core.database.dataSource.posts.PostsLocalDtsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceBinder {
    @Binds
    fun bindsPostsLocalDts(
        postsLocalDtsImpl: PostsLocalDtsImpl
    ): PostsLocalDts
}