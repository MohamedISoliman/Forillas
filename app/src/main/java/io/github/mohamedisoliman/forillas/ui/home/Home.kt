package io.github.mohamedisoliman.forillas.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import io.github.mohamedisoliman.forillas.data.entities.FeedPost


@Composable
fun Home(viewModel: HomeViewModel) {
    val viewState = viewModel.state().collectAsState()
    val postsList = viewState.value.postsListWithBodyTruncated

    if (postsList != null && postsList.isNotEmpty()) {
        PostsListView(list = postsList)
    }
}

@Composable
fun PostsListView(modifier: Modifier = Modifier, list: List<FeedPost>) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),

        verticalArrangement = Arrangement.SpaceBetween
    ) {

        list.forEachIndexed { index, feedPost ->
            item {
                PostView(item = feedPost) {
                    //TODO:navigate to Details
                }
            }
            if (index < list.lastIndex)
                item {
                    Divider(thickness = 1.dp)
                }
        }

    }
}

@Composable
fun PostView(
    modifier: Modifier = Modifier,
    item: FeedPost,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.title ?: "", style = MaterialTheme.typography.h5)
        Text(text = item.body ?: "", style = MaterialTheme.typography.body2)
    }
}
