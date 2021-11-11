package io.github.mohamedisoliman.forillas.domain

import io.github.mohamedisoliman.forillas.data.FeedRepository
import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

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
            .flowOn(dispatcher)
    }
}

sealed class HomeState(
    val isLoading: Boolean = false,
    val postsList: List<FeedPost>? = null,
    val throwable: Throwable? = null,
) {
    object EmptyResult : HomeState(postsList = emptyList())
    object Loading : HomeState(isLoading = true)
    data class Success(val result: List<FeedPost>) : HomeState(postsList = result)
    data class Failure(val error: Throwable) : HomeState(throwable = error)

}