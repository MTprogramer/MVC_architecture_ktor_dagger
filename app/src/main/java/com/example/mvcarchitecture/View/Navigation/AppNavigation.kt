package com.example.mvcapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mvcapp.ui.screens.posts.PostsScreen
import com.example.mvcarchitecture.Model.Repo.PostRepository
import com.example.mvcarchitecture.View.Screens.PostDetailScreen

@Composable
fun AppNavigation(repository: PostRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "posts") {
        composable("posts") { backStackEntry ->
            PostsScreen(
                repository = repository,
                lifecycleOwner = backStackEntry,
                onPostClick = { post ->
                    navController.navigate("postDetail/${post.id}")
                }
            )
        }
        composable(
            route = "postDetail/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: 0
            PostDetailScreen(
                postId = postId,
                repository = repository,
                lifecycleOwner = backStackEntry
            )
        }
    }
}