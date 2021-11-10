package io.github.mohamedisoliman.forillas.data

import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import io.github.mohamedisoliman.forillas.data.remote.RemoteFeedContract
import kotlinx.coroutines.flow.Flow

class FeedRepository(
    private val remote: RemoteFeedContract,
) : FeedRepositoryContract {

    override fun retrieveAllPosts(): Flow<List<FeedPost>> = remote.retrieveAllPosts()

    override fun retrievePost(id: Long): Flow<FeedPost> = remote.retrievePost(id)
}