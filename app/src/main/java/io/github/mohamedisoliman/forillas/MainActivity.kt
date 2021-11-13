package io.github.mohamedisoliman.forillas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.mohamedisoliman.forillas.navigation.AppNavigation
import io.github.mohamedisoliman.forillas.ui.theme.ForillasTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class MainActivity : ComponentActivity(), CoroutineScope by CoroutineScope(Dispatchers.Default) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            ForillasTheme {
                Scaffold {
                    AppNavigation(navController = navController)
                }

            }
        }
    }
}