package com.example.mvcarchitecture.Model.RepoImpl

import com.example.mvcapp.data.model.Post
import com.example.mvcarchitecture.Model.Api.ApiService
import com.example.mvcarchitecture.Model.Repo.PostRepository
import com.example.mvcarchitecture.Network.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject




class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PostRepository {
    override fun getPosts(): Flow<ApiResult<List<Post>>> {
        return apiService.getPosts()
    }

    override fun getPostById(postId: Int): Flow<ApiResult<Post>> {
        return apiService.getPostById(postId)
    }
}