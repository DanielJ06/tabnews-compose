package com.djr.tabnews.core.database.di

import com.djr.tabnews.core.database.TabnewsDatabase
import com.djr.tabnews.core.database.daos.PostsDao
import com.djr.tabnews.core.database.daos.TagsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesPostsDao(
        db: TabnewsDatabase
    ): PostsDao = db.postsDao()

    @Provides
    fun providesTagsDao(
        db: TabnewsDatabase
    ): TagsDao = db.tagsDao()

}