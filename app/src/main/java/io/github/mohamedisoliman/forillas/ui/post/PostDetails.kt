package io.github.mohamedisoliman.forillas.ui.post

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mohamedisoliman.forillas.data.entities.FeedPost

@Composable
fun PostDetails(post: FeedPost) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        ) {
        Text(text = post.title ?: "", style = MaterialTheme.typography.h5)
        Divider(modifier = Modifier.padding(vertical = 4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = post.name ?: "", style = MaterialTheme.typography.caption)
            Text(text = post.userName ?: "", style = MaterialTheme.typography.caption)
        }

        Divider(modifier = Modifier.padding(vertical = 4.dp))

        Text(text = post.body ?: "", style = MaterialTheme.typography.body1)


    }
}