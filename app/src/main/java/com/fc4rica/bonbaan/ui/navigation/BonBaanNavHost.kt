package com.fc4rica.bonbaan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fc4rica.bonbaan.ui.MockScreen
import com.fc4rica.bonbaan.ui.home.HomeScreen

@Composable
fun BonBaanNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            MockScreen("LOGIN")
        }
        composable(Screen.Register.route) {
            MockScreen("REGISTER")
        }
        composable(Screen.Interest.route) {
            MockScreen("INTEREST")
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
    }
}