package io.github.mohamedisoliman.forillas.data.remote

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import io.github.mohamedisoliman.forillas.GetAllPostsQuery
import io.github.mohamedisoliman.forillas.GetPostQuery
import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import io.github.mohamedisoliman.forillas.data.entities.PageOptions
import io.github.mohamedisoliman.forillas.type.PageQueryOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteFeed(private val apolloClient: ApolloClient) : RemoteFeedContract {

    override fun retrieveAllPosts(options: PageOptions): Flow<List<FeedPost>> = flow {
//
        val input = Input.optional(PageQueryOptions())
        val response = apolloClient.query(GetAllPostsQuery(input)).await()
        if (response.hasErrors()) {
            throw RuntimeException(response.errors?.firstOrNull()?.message)
        } else {
            val posts = response.data?.posts?.data?.mapNotNull {
                FeedPost(body = it?.body, title = it?.title, id = it?.id)
            }
            emit(posts ?: emptyList<FeedPost>())

        }

    }

    override fun retrievePost(id: Long): Flow<FeedPost> = flow {
        val response = apolloClient.query(GetPostQuery(id.toString())).await()
        if (response.hasErrors()) {
            throw RuntimeException(response.errors?.firstOrNull()?.message)
        } else {
            val post =
                response.data?.post?.let {
                    FeedPost(
                        body = it.body,
                        title = it.title,
                        id = it.id
                    )
                }
            emit(post ?: FeedPost())
        }
    }
}
