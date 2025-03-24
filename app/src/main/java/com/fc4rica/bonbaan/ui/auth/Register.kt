package com.fc4rica.bonbaan.ui.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fc4rica.bonbaan.ui.navigation.Screen

fun NavGraphBuilder.registerGraph(navController: NavHostController) {
    navigation(startDestination = Screen.InputEmail.route, route = Screen.Register.route) {
        composable(Screen.InputEmail.route) {
            InputEmailScreen(navController)
        }
        composable(Screen.PersonalInfo.route) {
            PersonalInfoScreen(navController)
        }
        composable(Screen.PasswordSetup.route) {
            PasswordSetupScreen((navController))
        }
        composable(Screen.EmailVerification.route) {
            EmailVerificationScreen(navController)
        }
    }
}