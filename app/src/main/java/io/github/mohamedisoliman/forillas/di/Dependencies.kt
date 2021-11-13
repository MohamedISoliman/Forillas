package io.github.mohamedisoliman.forillas.di

import io.github.mohamedisoliman.forillas.data.FeedRepository
import io.github.mohamedisoliman.forillas.data.apollo.apolloClient
import io.github.mohamedisoliman.forillas.data.apollo.okhttpClient
import io.github.mohamedisoliman.forillas.data.remote.RemoteFeed
import io.github.mohamedisoliman.forillas.domain.RetrieveAllPosts

object Dependencies {

    private val feedRepository by lazy {
        FeedRepository(RemoteFeed(apolloClient(okhttpClient())))
    }

    fun retrievePosts() = RetrieveAllPosts(feedRepository = feedRepository)
}