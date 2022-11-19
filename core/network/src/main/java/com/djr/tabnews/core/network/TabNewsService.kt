package com.djr.tabnews.core.network

import com.djr.tabnews.core.network.dtos.post.PostResponse
import retrofit2.http.GET

interface TabNewsService {
    @GET("contents")
    suspend fun getPosts(): List<PostResponse>
}