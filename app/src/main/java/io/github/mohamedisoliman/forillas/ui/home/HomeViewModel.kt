package io.github.mohamedisoliman.forillas.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import io.github.mohamedisoliman.forillas.domain.HomeState
import io.github.mohamedisoliman.forillas.domain.RetrieveAllPosts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val retrieveAllPosts: RetrieveAllPosts,
) : ViewModel() {

    private val state = MutableStateFlow<HomeState>(HomeState.Initial)
    fun state() = state.asLiveData()


    init {
        retrieveAllPosts.invoke()
            .onEach { state.value = it }
            .launchIn(viewModelScope)
    }


    fun retrievePost(id: String): FeedPost? =
        state.value.postsList?.firstOrNull { post -> post.id == id }

}

