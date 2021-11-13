package io.github.mohamedisoliman.forillas.navigation

import androidx.annotation.StringRes
import io.github.mohamedisoliman.forillas.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("route_home", R.string.home_screen)
    object PostDetails : Screen("route_post_details", R.string.post_details) {
        const val POST_ID_ARG = "postId"
    }
}