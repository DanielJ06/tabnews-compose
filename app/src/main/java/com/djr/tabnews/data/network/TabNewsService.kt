package com.djr.tabnews.data.network

import com.djr.tabnews.data.network.dtos.post.PostResponse
import retrofit2.http.GET

interface TabNewsService {
    @GET("contents")
    suspend fun getPosts(): List<PostResponse>
}