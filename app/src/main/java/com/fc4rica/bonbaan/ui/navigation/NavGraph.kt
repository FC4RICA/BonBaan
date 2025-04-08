package com.fc4rica.bonbaan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.fc4rica.bonbaan.ui.account.*

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "account") {
        composable("account") { AccountScreen(navController) }
        composable("profile") { ProfileScreen() }
        composable("reviews") { ReviewsScreen() }
    }
}