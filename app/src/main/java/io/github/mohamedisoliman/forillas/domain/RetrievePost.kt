package io.github.mohamedisoliman.forillas.domain

import io.github.mohamedisoliman.forillas.data.FeedRepository
import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class RetrievePost(
    private val feedRepository: FeedRepository,
    private val dispatcher: CoroutineDispatcher,
) : (String) -> Flow<FeedPost> {

    override fun invoke(id: String): Flow<FeedPost> {
        return feedRepository.retrievePost(id).flowOn(dispatcher)
    }


}