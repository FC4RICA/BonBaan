package com.fc4rica.bonbaan.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fc4rica.bonbaan.ui.theme.BonBaanTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fc4rica.bonbaan.ui.home.BottomNavBar
import com.fc4rica.bonbaan.ui.home.HomeSection

@Composable
fun BonBaanApp() {
    BonBaanTheme {
        val navController = rememberNavController()

        MainScreen(navController)
    }
}

@Composable
fun MainScreen(
    navController: NavHostController
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = currentDestination ?: HomeSection.FEED.route,
                navigateToRoute = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeSection.FEED.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(HomeSection.FEED.route) {
                MockScreen("FEED")
            }
            composable(HomeSection.CHAT.route) {
                MockScreen("CHAT")
            }
            composable(HomeSection.NOTIFICATION.route) {
                MockScreen("NOTIFICATION")
            }
            composable(HomeSection.PROFILE.route) {
                MockScreen("PROFILE")
            }
        }
    }
}

@Composable
fun MockScreen(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun BonBaanAppPreview() {
    BonBaanApp()
}