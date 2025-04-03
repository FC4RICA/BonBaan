package com.fc4rica.bonbaan.ui.auth

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fc4rica.bonbaan.ui.auth.register.registerGraph
import com.fc4rica.bonbaan.ui.navigation.Screen

fun NavGraphBuilder.authenticationGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Login.route, route = Screen.Auth.route) {

        composable(
            Screen.Login.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(500)
                )
            }) {
            LoginScreen(
                onLoginSuccess = { isFirstTime ->
                    if (isFirstTime) {
                        navController.navigate(Screen.Onboarding.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                },
                navigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        registerGraph(navController)
    }
}