package com.creativijaya.basiccompose.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.creativijaya.basiccompose.ui.main.bookmark.BookmarkPage
import com.creativijaya.basiccompose.ui.main.home.HomePage
import kotlinx.coroutines.launch

private val mainPageDestinations = listOf(
    MainPageDestinations.Home,
    MainPageDestinations.Bookmark
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val mainNavController = rememberNavController()
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            val backStackState by mainNavController.currentBackStackEntryAsState()
            val currentRoute = backStackState?.destination?.route

            ModalDrawerSheet(
                modifier = Modifier.width(280.dp)
            ) {
                mainPageDestinations.map { destination ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = destination.label)
                        },
                        selected = currentRoute == destination.route,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }

                            mainNavController.navigate(destination.route)
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                            .padding(top = 12.dp)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Rounded.Menu, contentDescription = "")
                        }
                    },
                    title = {
                        Text("Basic Compose")
                    },
                )
            },
        ) {
            Surface(modifier = Modifier.padding(it)) {
                NavHost(
                    navController = mainNavController,
                    startDestination = MainPageDestinations.Home.route
                ) {
                    composable(MainPageDestinations.Home.route) {
                        HomePage()
                    }

                    composable(MainPageDestinations.Bookmark.route) {
                        BookmarkPage()
                    }
                }
            }
        }
    }
}
