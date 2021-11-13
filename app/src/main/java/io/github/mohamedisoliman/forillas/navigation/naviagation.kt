package io.github.mohamedisoliman.forillas.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.mohamedisoliman.forillas.di.Dependencies
import io.github.mohamedisoliman.forillas.navigation.Screen.Home
import io.github.mohamedisoliman.forillas.navigation.Screen.PostDetails
import io.github.mohamedisoliman.forillas.ui.home.Home
import io.github.mohamedisoliman.forillas.ui.home.HomeViewModel
import io.github.mohamedisoliman.forillas.ui.home.HomeViewModelFactory
import io.github.mohamedisoliman.forillas.ui.post.PostDetails


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val viewModel: HomeViewModel =
        viewModel(factory = HomeViewModelFactory(Dependencies.retrievePosts()))

    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {

        composable(Home.route) {
            Home(viewModel) {
                navController.navigate("${PostDetails.route}/${it}")
            }
        }

        composable(
            route = "${PostDetails.route}/{${PostDetails.POST_ID_ARG}}",
            arguments = listOf(navArgument(PostDetails.POST_ID_ARG) { type = NavType.StringType })
        ) { entry ->

            val id = entry.arguments?.getString(PostDetails.POST_ID_ARG)
            val post = id?.let { viewModel.retrievePost(it) }
            if (post != null) {
                PostDetails(post)
            }
        }

    }

}


