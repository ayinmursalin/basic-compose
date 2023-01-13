package com.creativijaya.basiccompose.ui.main

import androidx.annotation.DrawableRes
import com.creativijaya.basiccompose.R

sealed class MainPageDestinations(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int
) {
    object Home : MainPageDestinations(
        route = "home",
        label = "Homepage",
        icon = R.drawable.round_home_24
    )

    object Bookmark : MainPageDestinations(
        route = "bookmark",
        label = "Bookmark",
        icon = R.drawable.round_bookmarks_24
    )

    companion object {
        const val ROUTE_NAME = "main"
    }
}
