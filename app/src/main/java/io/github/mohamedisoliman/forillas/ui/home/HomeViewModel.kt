package io.github.mohamedisoliman.forillas.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import io.github.mohamedisoliman.forillas.domain.HomeState
import io.github.mohamedisoliman.forillas.domain.RetrieveAllPosts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val retrieveAllPosts: RetrieveAllPosts,
) : ViewModel() {

    private val state = MutableStateFlow<HomeState>(HomeState.Initial)
    fun state() = state.asStateFlow()


    init {
        retrieveAllPosts.invoke()
            .onEach { state.value = it }
            .launchIn(viewModelScope)
    }


    fun retrievePost(id: String): FeedPost? =
        state.value.postsList?.firstOrNull { post -> post.id == id }

}

