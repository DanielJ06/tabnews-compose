package com.djr.tabnews.features.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djr.tabnews.domain.Resource
import com.djr.tabnews.domain.useCases.posts.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _postsState = MutableStateFlow(HomeState())
    val postState: StateFlow<HomeState> = _postsState

    init {
        handleGetPosts()
    }

    private fun handleGetPosts() {
        viewModelScope.launch {
            getPostsUseCase.invoke().collect {
                when (it) {
                    is Resource.Success -> {
                        _postsState.value = HomeState(posts = it.data ?: emptyList())
                    }
                    is Resource.Loading -> {
                        _postsState.value = HomeState(isLoading = true)
                    }
                    is Resource.Error -> {
                        _postsState.value =
                            HomeState(error = it.message ?: "An unexpected error occurred")
                    }
                }
            }
        }
    }

}