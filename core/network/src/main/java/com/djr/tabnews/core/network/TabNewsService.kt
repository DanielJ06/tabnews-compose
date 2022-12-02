package com.djr.tabnews.core.network

import com.djr.tabnews.core.network.dtos.post.PostContentResponse
import com.djr.tabnews.core.network.dtos.post.PostRepliesResponse
import com.djr.tabnews.core.network.dtos.post.PostResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TabNewsService {
    @GET("contents")
    suspend fun getPosts(): List<PostResponse>

    @GET("contents/{owner}/{slug}")
    suspend fun getPostDetail(
        @Path("owner") owner: String,
        @Path("slug") slug: String
    ): PostContentResponse

    @GET("contents/{owner}/{slug}/children")
    suspend fun getPostReplies(
        @Path("owner") owner: String,
        @Path("slug") slug: String
    ): List<PostRepliesResponse>
}