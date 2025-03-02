package com.example.mvcarchitecture

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mvcapp.ui.navigation.AppNavigation
import com.example.mvcarchitecture.Model.Repo.PostRepository
import com.example.mvcarchitecture.View.theme.MVCarchitectureTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
open class MvcApplication : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: PostRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVCarchitectureTheme {
                Scaffold { padding ->
                    Box(modifier = Modifier.padding(padding))
                    {
                        AppNavigation(repository = repository)
                    }
                }
            }
        }
    }
}