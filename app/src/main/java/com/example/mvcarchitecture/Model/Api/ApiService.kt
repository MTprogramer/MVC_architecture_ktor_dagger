package com.example.mvcarchitecture.Model.Api

import com.example.mvcapp.data.model.Post
import com.example.mvcarchitecture.Network.ApiResult
import kotlinx.coroutines.flow.Flow

interface ApiService {
    fun getPosts(): Flow<ApiResult<List<Post>>>
    fun getPostById(postId: Int): Flow<ApiResult<Post>> // Added
}

