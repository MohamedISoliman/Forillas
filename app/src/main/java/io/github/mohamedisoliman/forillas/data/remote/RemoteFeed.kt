package io.github.mohamedisoliman.forillas.data.remote

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import io.github.mohamedisoliman.forillas.AllPostsQuery
import io.github.mohamedisoliman.forillas.GetPostQuery
import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import io.github.mohamedisoliman.forillas.data.entities.PageOptions
import io.github.mohamedisoliman.forillas.type.PageQueryOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteFeed(private val apolloClient: ApolloClient) : RemoteFeedContract {

    override fun retrieveAllPosts(options: PageOptions): Flow<List<FeedPost>> = flow {

        val input = Input.optional(PageQueryOptions())
        val response = apolloClient.query(AllPostsQuery(input)).await()
        val posts = response.data?.posts?.data?.mapNotNull { it?.toFeedPost() }

        emit(posts ?: emptyList())
    }

    override fun retrievePost(id: Long): Flow<FeedPost> = flow {
        val response = apolloClient.query(GetPostQuery(id.toString())).await()
        val post = response.data?.post?.toFeedPost()
        emit(post ?: FeedPost())
    }
}

private fun GetPostQuery.Post.toFeedPost() = FeedPost(
    id = this.id,
    title = this.title,
    body = this.body
)

private fun AllPostsQuery.Data1.toFeedPost() = FeedPost(
    id = this.id,
    title = this.title,
    body = this.body
)
