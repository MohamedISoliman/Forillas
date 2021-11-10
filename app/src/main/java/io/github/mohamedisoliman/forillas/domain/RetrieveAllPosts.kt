package io.github.mohamedisoliman.forillas.domain

import io.github.mohamedisoliman.forillas.data.FeedRepository
import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class RetrieveAllPosts(
    private val feedRepository: FeedRepository,
    private val dispatcher: CoroutineDispatcher,
) : () -> Flow<List<FeedPost>> {

    override fun invoke(): Flow<List<FeedPost>> {
        return feedRepository.retrieveAllPosts().flowOn(dispatcher)
    }


}