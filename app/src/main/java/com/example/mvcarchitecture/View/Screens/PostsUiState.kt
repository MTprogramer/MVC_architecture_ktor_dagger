package com.example.mvcapp.ui.screens.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.example.mvcapp.data.model.Post
import com.example.mvcarchitecture.Model.Repo.PostRepository
import com.example.mvcarchitecture.Network.ApiResult
import kotlinx.coroutines.flow.Flow

@Composable
fun PostsScreen(
    repository: PostRepository,
    lifecycleOwner: LifecycleOwner,
    onPostClick: (Post) -> Unit
) {
    val controller = remember { PostsController(repository, lifecycleOwner) }
    val state by controller.postsFlow.collectAsState(initial = ApiResult.Loading)

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
                PostsList(posts = (state as ApiResult.Success).data, onPostClick = onPostClick)
            }
            is ApiResult.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Error: ${(state as ApiResult.Error).exception.message}",
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { controller.fetchPosts() }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}

@Composable
fun PostsList(posts: List<Post>, onPostClick: (Post) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(posts) { post ->
            PostItem(post = post, onClick = { onPostClick(post) })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PostItem(post: Post, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}