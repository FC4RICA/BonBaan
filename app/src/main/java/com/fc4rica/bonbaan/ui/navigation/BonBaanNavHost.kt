package com.fc4rica.bonbaan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fc4rica.bonbaan.domain.usecase.user.UserUseCase
import com.fc4rica.bonbaan.ui.auth.RegisterViewModel
import com.fc4rica.bonbaan.ui.auth.authenticationGraph
import com.fc4rica.bonbaan.ui.home.HomeScreen
import com.fc4rica.bonbaan.ui.onboarding.onboardingGraph

@Composable
fun BonBaanNavHost(navController: NavHostController, userUseCase: UserUseCase) {

    val registerViewModel: RegisterViewModel = viewModel(
        factory = RegisterViewModel.provideFactory(userUseCase)
    )

    NavHost(navController = navController, startDestination = Screen.Auth.route) {
        authenticationGraph(navController, registerViewModel)
        onboardingGraph(navController)
        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}