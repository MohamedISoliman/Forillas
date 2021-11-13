package io.github.mohamedisoliman.forillas.domain

import io.github.mohamedisoliman.forillas.data.FeedRepository
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

