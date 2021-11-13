package io.github.mohamedisoliman.forillas.domain

import io.github.mohamedisoliman.forillas.data.entities.FeedPost

private const val POST_BODY_LIMIT = 120


sealed class HomeState(
    val isLoading: Boolean = false,
    val postsList: List<FeedPost>? = null,
    val throwable: Throwable? = null,
) {
    object Initial : HomeState()
    object EmptyResult : HomeState(postsList = emptyList())
    object Loading : HomeState(isLoading = true)
    data class Success(val result: List<FeedPost>) : HomeState(postsList = result)
    data class Failure(val error: Throwable) : HomeState(throwable = error)

    val postsListWithBodyTruncated = postsList?.map { item ->
        item.copy(body = item.body?.take(POST_BODY_LIMIT))
    }

}