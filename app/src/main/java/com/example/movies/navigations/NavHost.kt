package com.example.movies.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
    }
}