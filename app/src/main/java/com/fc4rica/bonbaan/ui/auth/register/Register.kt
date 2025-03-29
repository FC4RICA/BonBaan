package com.fc4rica.bonbaan.ui.auth.register

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fc4rica.bonbaan.ui.navigation.Screen

fun NavGraphBuilder.registerGraph(navController: NavHostController) {
    navigation(startDestination = Screen.InputEmail.route, route = Screen.Register.route) {
        composable(
            Screen.InputEmail.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(700)
                )
            }
        ) {
            InputEmailScreen(
                navigateToLogin = { navController.navigate(Screen.Login.route) },
                navigateToPersonalInfo = { navController.navigate(Screen.PersonalInfo.route) }
            )
        }
        composable(
            Screen.PersonalInfo.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(700)
                )
            }
        ) {
            PersonalInfoScreen({ navController.navigate(Screen.PasswordSetup.route) })
        }
        composable(
            Screen.PasswordSetup.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(700)
                )
            }
        ) {
            PasswordSetupScreen({ navController.navigate(Screen.EmailVerification.route) }
            )
        }
        composable(
            Screen.EmailVerification.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(700)
                )
            }
        ) {
            EmailVerificationScreen({ navController.navigate(Screen.Login.route) })
        }
    }
}