package com.fc4rica.bonbaan.ui.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fc4rica.bonbaan.ui.navigation.Screen

fun NavGraphBuilder.onboardingGraph(navController: NavHostController) {

    navigation(startDestination = Screen.Interest.route, route = Screen.Onboarding.route) {
//        composable(Screen.Welcome.route) {
//            WelcomeScreen()
//        }
        composable(Screen.Interest.route) {
            InterestScreen(
                onSuccess = { navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Interest.route) { inclusive = true }
                }}
            )
        }

    }
}

