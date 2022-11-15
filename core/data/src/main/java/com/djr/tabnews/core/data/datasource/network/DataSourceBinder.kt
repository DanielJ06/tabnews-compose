package com.djr.tabnews.core.data.datasource.network

import com.djr.tabnews.core.network.dataSource.PostsNetworkDts
import com.djr.tabnews.core.network.dataSource.PostsNetworkDtsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceBinder {
    @Binds
    fun bindsNetworkDataSource(
        postsNetworkDtsImpl: PostsNetworkDtsImpl
    ): PostsNetworkDts
}