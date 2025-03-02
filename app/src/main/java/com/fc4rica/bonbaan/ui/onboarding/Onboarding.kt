package com.fc4rica.bonbaan.ui.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fc4rica.bonbaan.ui.MockScreen
import com.fc4rica.bonbaan.ui.navigation.Screen

fun NavGraphBuilder.onboardingGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Welcome.route, route = Screen.Onboarding.route) {
        composable(Screen.Welcome.route) {
            MockScreen("WELCOME")
        }
        composable(Screen.Interest.route) {
            MockScreen("INTEREST")
        }

    }
}