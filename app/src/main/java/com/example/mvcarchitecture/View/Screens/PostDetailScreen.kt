package com.example.mvcarchitecture.View.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.example.mvcarchitecture.Controler.PostDetailController
import com.example.mvcarchitecture.Model.Repo.PostRepository
import com.example.mvcarchitecture.Network.ApiResult
import kotlinx.coroutines.flow.Flow

@Composable
fun PostDetailScreen(
    postId: Int,
    repository: PostRepository,
    lifecycleOwner: LifecycleOwner
) {
    val controller = remember { PostDetailController(postId, repository, lifecycleOwner) }
    val state by controller.postFlow.collectAsState(initial = ApiResult.Loading)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (state) {
            is ApiResult.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize().wrapContentSize()
                )
            }
            is ApiResult.Success -> {
                val post = (state as ApiResult.Success).data
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Post ID: ${post.id}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = post.body,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            is ApiResult.Error -> {
                Text(
                    text = "Error: ${(state as ApiResult.Error).exception.message}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                        .padding(16.dp)
                )
            }
        }
    }
}