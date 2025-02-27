package com.fc4rica.bonbaan.ui.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fc4rica.bonbaan.ui.MockScreen
import com.fc4rica.bonbaan.ui.navigation.Screen

fun NavGraphBuilder.authenticationGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Login.route, route = Screen.Auth.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }
        composable(Screen.Register.route) {
            MockScreen("REGISTER")
        }

    }
}