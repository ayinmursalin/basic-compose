package com.creativijaya.basiccompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.creativijaya.basiccompose.ui.main.MainPageDestinations
import com.creativijaya.basiccompose.ui.main.mainPageGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = MainPageDestinations.ROUTE_NAME
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        mainPageGraph()
    }
}
