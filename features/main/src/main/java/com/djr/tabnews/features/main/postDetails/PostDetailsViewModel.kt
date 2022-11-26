package com.djr.tabnews.features.main.postDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djr.tabnews.core.domain.Resource
import com.djr.tabnews.core.domain.useCases.posts.GetPostDetail
import com.djr.tabnews.features.main.postDetails.navigation.PostDetailsArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPostDetail: GetPostDetail
) : ViewModel() {

    private val args = PostDetailsArgs(savedStateHandle)

    private val _postDetailState = MutableStateFlow(PostDetailsState())
    val postDetailState: StateFlow<PostDetailsState> = _postDetailState

    fun handleGetPostDetail() {
        viewModelScope.launch {
            getPostDetail.invoke(args.owner, args.slug).collect {
                when (it) {
                    is Resource.Success -> {
                        _postDetailState.value = PostDetailsState(postDetail = it.data)
                    }
                    is Resource.Loading -> {
                        _postDetailState.value = PostDetailsState(isLoading = true)
                    }
                    is Resource.Error -> {
                        _postDetailState.value = PostDetailsState(
                            error = it.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }

}