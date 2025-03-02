package com.example.mvcapp.ui.screens.posts

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.mvcapp.data.model.Post
import com.example.mvcarchitecture.Model.Repo.PostRepository
import com.example.mvcarchitecture.Network.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch

class PostsController(
    private val repository: PostRepository,
    private val lifecycleOwner: LifecycleOwner
) {
    val postsFlow: Flow<ApiResult<List<Post>>> = repository.getPosts()

    fun fetchPosts() {  // triggers with retry button
        lifecycleOwner.lifecycleScope.launch {
            postsFlow.collect { /* No-op: Flow is collected in UI */ }
        }
    }
}