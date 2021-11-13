package io.github.mohamedisoliman.forillas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.mohamedisoliman.forillas.data.FeedRepository
import io.github.mohamedisoliman.forillas.data.apollo.apolloClient
import io.github.mohamedisoliman.forillas.data.apollo.okhttpClient
import io.github.mohamedisoliman.forillas.data.remote.RemoteFeed
import io.github.mohamedisoliman.forillas.di.Dependencies
import io.github.mohamedisoliman.forillas.ui.home.Home
import io.github.mohamedisoliman.forillas.ui.home.HomeViewModel
import io.github.mohamedisoliman.forillas.ui.home.HomeViewModelFactory
import io.github.mohamedisoliman.forillas.ui.theme.ForillasTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import logcat.logcat

class MainActivity : ComponentActivity(), CoroutineScope by CoroutineScope(Dispatchers.Default) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myViewModel by viewModels<HomeViewModel> {
            HomeViewModelFactory(Dependencies.retrievePosts())
        }

        setContent {
            ForillasTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Home(viewModel = myViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ForillasTheme {
        Greeting("Android")
    }
}