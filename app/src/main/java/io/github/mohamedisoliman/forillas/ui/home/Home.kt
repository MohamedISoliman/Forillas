package io.github.mohamedisoliman.forillas.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.mohamedisoliman.forillas.R
import io.github.mohamedisoliman.forillas.data.entities.FeedPost
import io.github.mohamedisoliman.forillas.domain.HomeState
import io.github.mohamedisoliman.forillas.ui.common.PlaceHolderView


@Composable
fun Home(
    viewModel: HomeViewModel,
    onItemClicked: (String) -> Unit = {},
) {
    val viewState = viewModel.state().collectAsState()
    val value: HomeState = viewState.value
    HomeContent(value, onItemClicked)
}

@Composable
private fun HomeContent(
    state: HomeState,
    onItemClicked: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        LoadingView(modifier = Modifier.align(Alignment.Center), isLoading = state.isLoading)
        PostsListView(list = state.postsListWithBodyTruncated, onItemClicked = onItemClicked)
        EmptyView(list = state.postsList)
        ErrorView(throwable = state.throwable)
    }
}

@Composable
fun EmptyView(modifier: Modifier = Modifier, list: List<FeedPost>?) {
    if (list?.isEmpty() == true) {
        PlaceHolderView(modifier = modifier, icon = Icons.Outlined.Info,
            message = stringResource(R.string.emptyViewMessage))
    }
}

@Composable
fun ErrorView(modifier: Modifier = Modifier, throwable: Throwable?) {
    throwable?.let {
        PlaceHolderView(
            modifier = modifier, icon = Icons.Outlined.Warning,
            message = throwable.message ?: stringResource(R.string.defaultErrorMessage),
            textColor = MaterialTheme.colors.error
        )
    }
}

@Composable
fun LoadingView(modifier: Modifier = Modifier, isLoading: Boolean?) {
    if (isLoading == true) {
        CircularProgressIndicator(
            modifier = modifier
                .size(48.dp)
        )
    }
}

@Composable
fun PostsListView(
    modifier: Modifier = Modifier,
    list: List<FeedPost>?,
    onItemClicked: (String) -> Unit,
) {
    if (list?.isNotEmpty() == true) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            list.forEachIndexed { index, feedPost ->
                item {
                    PostView(item = feedPost) { id ->
                        if (id != null) {
                            onItemClicked(id)
                        }
                    }
                }
                if (index < list.lastIndex)
                    item {
                        Divider(thickness = 1.dp)
                    }
            }

        }
    }
}

@Composable
fun PostView(
    modifier: Modifier = Modifier,
    item: FeedPost,
    onClick: (String?) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable { onClick(item.id) },
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.title ?: "", style = MaterialTheme.typography.h5)
        Text(text = item.body ?: "", style = MaterialTheme.typography.body2)
    }
}
