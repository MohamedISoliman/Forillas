package io.github.mohamedisoliman.forillas.domain

import io.github.mohamedisoliman.forillas.data.FeedRepository
import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import io.github.mohamedisoliman.forillas.data.remote.RemoteFeed
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import logcat.logcat
import org.junit.Assert.*
import org.junit.Test

class RetrieveAllPostsTest {


    @Test
    fun `WHEN RetrieveAllPosts() invoked THEN start with Loading state`(): Unit = runBlocking {
        //GIVEN
        val remote = mockk<RemoteFeed>()
        val repo = FeedRepository(remote)
        val interactor = RetrieveAllPosts(feedRepository = repo)

        //WHEN
        every { remote.retrieveAllPosts() } returns flowOf(emptyList())

        //THEN
        val first = interactor.invoke().first()
        assertTrue(first is HomeState.Loading)
    }

    @Test
    fun `GIVEN remote return emptyList WHEN RetrieveAllPosts() invoked THEN return Empty state`(): Unit =
        runBlocking {
            //GIVEN
            val remote = mockk<RemoteFeed>()
            val repo = FeedRepository(remote)
            val interactor = RetrieveAllPosts(feedRepository = repo)
            every { remote.retrieveAllPosts() } returns flowOf(emptyList())

            //WHEN
            val first = interactor.invoke().drop(1).first()

            //THEN
            assertTrue(first is HomeState.EmptyResult)
        }

    @Test
    fun `GIVEN remote return result WHEN RetrieveAllPosts() invoked THEN return Success state`(): Unit =
        runBlocking {
            //GIVEN
            val post = FeedPost()
            val remote = mockk<RemoteFeed>()
            val repo = FeedRepository(remote)
            val interactor = RetrieveAllPosts(feedRepository = repo)
            val expected = listOf(post, post, post)

            every { remote.retrieveAllPosts() } returns flowOf(expected)

            //WHEN
            val state = interactor.invoke().drop(1).first()

            //THEN
            assertTrue(state is HomeState.Success && state.result.size == expected.size)
        }


    @Test
    fun `GIVEN remote throw error WHEN RetrieveAllPosts() invoked THEN return Failure state`(): Unit =
        runBlocking {
            //GIVEN
            val remote = mockk<RemoteFeed>()
            val repo = FeedRepository(remote)
            val interactor = RetrieveAllPosts(feedRepository = repo)
            val message = "Some error happened"
            every { remote.retrieveAllPosts() } returns flow { throw RuntimeException(message) }

            //WHEN
            val first = interactor.invoke().drop(1).first()

            //THEN
            assertTrue((first as HomeState.Failure).error is RuntimeException)
            assertTrue(first.error.message == message)
        }


}