package com.example.mvcarchitecture.Controler

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.mvcapp.data.model.Post
import com.example.mvcarchitecture.Model.Repo.PostRepository
import com.example.mvcarchitecture.Network.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch

class PostDetailController(
    private val postId: Int,
    private val repository: PostRepository,
    private val lifecycleOwner: LifecycleOwner
) {

    /*
 Plain Flow (Current):
     Cold Flow: Starts fetching data only when collected, auto-emits to UI without manual updates.
     Fresh fetch per collection, no retained state, simpler Controller with less code.
 StateFlow (Alternative):
     Hot Flow: Emits the last value to new collectors instantly, requires manual updates (_state.value =).
     Retains state for persistence, more complex Controller with greater control.
 */

    val postFlow: Flow<ApiResult<Post>> = repository.getPostById(postId)   // simple flow auto fetch
        .retry(3)
        .catch { emit(ApiResult.Error(it)) }

    fun fetchPosts() {
        lifecycleOwner.lifecycleScope.launch {
            postFlow.collect { /* No-op: Flow is collected in UI */ }
        }
    }

}