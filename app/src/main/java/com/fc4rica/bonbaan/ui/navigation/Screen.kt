package com.fc4rica.bonbaan.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Interest : Screen("interest")
    object Home : Screen("home")
}