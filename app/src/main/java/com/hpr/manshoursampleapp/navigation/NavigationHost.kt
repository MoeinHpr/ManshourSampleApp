package com.hpr.manshoursampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hpr.cinema.CinemaComposable

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoutes.CinemaComposable.route) {
        composable(NavRoutes.CinemaComposable.route) {
            CinemaComposable()
        }
    }
}