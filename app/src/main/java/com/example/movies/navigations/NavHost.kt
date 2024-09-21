package com.example.movies.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun navigation() {
    val navControl = rememberNavController()
    NavHost(navController = navControl, startDestination = "BannerScreen") {
        composable("BannerScreen") {
            BannerScreen(navController = navControl)
        }

        composable("HomeScreen") {
            HomeScreen(navController = navControl)
        }

        composable(
            "DetailsScreen/{id}",
            arguments = listOf(
                navArgument(
                    name = "id",

                    ) {
                    type = NavType.IntType
                }
            )
        ) { id ->
            id.arguments?.getInt("id")?.let { id1 ->
                DetailsScreen(id = id1)
            }
        }
    }
}