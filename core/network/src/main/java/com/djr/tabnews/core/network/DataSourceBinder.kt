package com.djr.tabnews.core.network

import com.djr.tabnews.core.network.dataSource.PostsNetworkDts
import com.djr.tabnews.core.network.dataSource.PostsNetworkDtsImpl
import com.djr.tabnews.core.network.dataSource.auth.AuthNetworkDts
import com.djr.tabnews.core.network.dataSource.auth.AuthNetworkDtsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceBinder {

    //-----| Auth Data Source |-----//
    @Binds
    fun bindsAuthNetworkDts(
        authNetworkDtsImpl: AuthNetworkDtsImpl
    ): AuthNetworkDts

    //-----| Posts Data Source |-----//
    @Binds
    fun bindsPostsNetworkDts(
        postsNetworkDtsImpl: PostsNetworkDtsImpl
    ): PostsNetworkDts
}