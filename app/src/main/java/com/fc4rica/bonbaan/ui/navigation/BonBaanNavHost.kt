package com.fc4rica.bonbaan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fc4rica.bonbaan.ui.auth.authenticationGraph
import com.fc4rica.bonbaan.ui.home.HomeScreen
import com.fc4rica.bonbaan.ui.onboarding.onboardingGraph

@Composable
fun BonBaanNavHost(
    navController: NavHostController,
    isAuthenticated: Boolean,
    hasSelectedInterests: Boolean
) {
    val startDestination = when {
        !isAuthenticated -> Screen.Auth.route
        !hasSelectedInterests -> Screen.Onboarding.route
        else -> Screen.Home.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        authenticationGraph(navController)
        onboardingGraph(navController)
        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}