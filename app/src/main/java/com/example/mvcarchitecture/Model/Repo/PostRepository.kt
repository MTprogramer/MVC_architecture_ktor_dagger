package com.example.mvcarchitecture.Model.Repo

import com.example.mvcapp.data.model.Post
import com.example.mvcarchitecture.Network.ApiResult
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<ApiResult<List<Post>>>
    fun getPostById(postId: Int): Flow<ApiResult<Post>> // Added
}