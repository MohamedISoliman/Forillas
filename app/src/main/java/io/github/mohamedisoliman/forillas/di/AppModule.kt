package io.github.mohamedisoliman.forillas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.mohamedisoliman.forillas.data.FeedRepository
import io.github.mohamedisoliman.forillas.domain.FeedRepositoryContract
import io.github.mohamedisoliman.forillas.data.apollo.apolloClient
import io.github.mohamedisoliman.forillas.data.apollo.okhttpClient
import io.github.mohamedisoliman.forillas.data.remote.RemoteFeed
import io.github.mohamedisoliman.forillas.data.remote.RemoteFeedContract
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun provideFeedRemote(): RemoteFeedContract {
        return RemoteFeed(apolloClient(okhttpClient()))
    }


    @Provides
    fun provideFeedRepository(repository: FeedRepository): FeedRepositoryContract {
        return repository
    }

}