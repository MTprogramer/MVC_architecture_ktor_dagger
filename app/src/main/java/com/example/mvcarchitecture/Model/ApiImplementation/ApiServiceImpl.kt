package com.example.mvcarchitecture.Model.ApiImplementation

import com.example.mvcapp.data.model.Post
import com.example.mvcarchitecture.Model.Api.ApiService
import com.example.mvcarchitecture.Network.ApiResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ApiServiceImpl(
    private val client: HttpClient,
    private val baseUrl: String = "https://jsonplaceholder.typicode.com/" // Still parameterized
) : ApiService {
    override fun getPosts(): Flow<ApiResult<List<Post>>> = flow {
        emit(ApiResult.Loading)
        try {
            val posts = client.get("${baseUrl}posts").body<List<Post>>()
            emit(ApiResult.Success(posts))
        } catch (e: Exception) {
            emit(ApiResult.Error(e))
        }
    }

    override fun getPostById(postId: Int): Flow<ApiResult<Post>> = flow {
        emit(ApiResult.Loading)
        try {
            val post = client.get("${baseUrl}posts/$postId").body<Post>()
            emit(ApiResult.Success(post))
        } catch (e: Exception) {
            emit(ApiResult.Error(e))
        }
    }
}