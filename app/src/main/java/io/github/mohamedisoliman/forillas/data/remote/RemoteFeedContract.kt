package io.github.mohamedisoliman.forillas.data.remote

import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import io.github.mohamedisoliman.forillas.data.entities.PageOptions
import kotlinx.coroutines.flow.Flow

interface RemoteFeedContract {

    fun retrieveAllPosts(options: PageOptions = PageOptions()): Flow<List<FeedPost>>

    fun retrievePost(id: String): Flow<FeedPost>
}