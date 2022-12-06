package com.djr.tabnews.features.main.post.replyChild

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.djr.tabnews.features.main.post.replyChild.navigation.ReplyChildrenArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ReplyChildrenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val args: ReplyChildrenArgs = ReplyChildrenArgs(savedStateHandle)

    private val _replyChildrenState = MutableStateFlow(ReplyChildrenState())
    val replyChildrenState: StateFlow<ReplyChildrenState> = _replyChildrenState.asStateFlow()

    init {
        _replyChildrenState.value = ReplyChildrenState(args.thread)
    }

}