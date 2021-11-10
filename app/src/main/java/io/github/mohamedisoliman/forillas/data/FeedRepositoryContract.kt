package io.github.mohamedisoliman.forillas.data

import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import kotlinx.coroutines.flow.Flow

interface FeedRepositoryContract {

    fun retrieveAllPosts(): Flow<List<FeedPost>>

    fun retrievePost(id: String): Flow<FeedPost>
}