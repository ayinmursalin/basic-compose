package com.creativijaya.basiccompose.ui.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.mainPageGraph() {
    composable(route = MainPageDestinations.ROUTE_NAME) {
        MainPage()
    }
}
