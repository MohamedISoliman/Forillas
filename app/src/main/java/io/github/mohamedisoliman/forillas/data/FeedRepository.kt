package io.github.mohamedisoliman.forillas.data

import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import io.github.mohamedisoliman.forillas.data.remote.RemoteFeedContract
import io.github.mohamedisoliman.forillas.domain.FeedRepositoryContract
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val remote: RemoteFeedContract,
) : FeedRepositoryContract {

    override fun retrieveAllPosts(): Flow<List<FeedPost>> = remote.retrieveAllPosts()

}