package io.github.mohamedisoliman.forillas.domain

import io.github.mohamedisoliman.forillas.data.FeedRepository
import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import logcat.logcat

class RetrieveAllPosts(
    private val feedRepository: FeedRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : () -> Flow<HomeState> {

    override fun invoke(): Flow<HomeState> {
        return feedRepository.retrieveAllPosts()
            .map {
                if (it.isEmpty())
                    HomeState.EmptyResult
                else
                    HomeState.Success(result = it)
            }
            .catch { emit(HomeState.Failure(it)) }
            .onStart { emit(HomeState.Loading) }
            .onEach { logcat { it.toString() } }
            .flowOn(dispatcher)
    }
}

sealed class HomeState(
    val isLoading: Boolean = false,
    val postsList: List<FeedPost>? = null,
    val throwable: Throwable? = null,
) {
    object Initial : HomeState()
    object EmptyResult : HomeState(postsList = emptyList())
    object Loading : HomeState(isLoading = true)
    data class Success(val result: List<FeedPost>) : HomeState(postsList = result)
    data class Failure(val error: Throwable) : HomeState(throwable = error)

    private val BODY_LIMIT = 20
    val postsListWithBodyTruncated = postsList?.map { item ->
        item.copy(body = item.body?.take(BODY_LIMIT))
    }

}