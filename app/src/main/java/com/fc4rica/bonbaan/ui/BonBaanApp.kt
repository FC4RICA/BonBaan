package com.fc4rica.bonbaan.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fc4rica.bonbaan.ui.theme.BonBaanTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BonBaanApp() {
    BonBaanTheme {
        val navController = rememberNavController()
        val screens = listOf("Home", "Chat", "Notify", "Profile")

        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    screens.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(Icons.Default.Home, contentDescription = screen) },
                            label = { Text(screen) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen } == true,
                            onClick = {
                                navController.navigate(screen) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "Home", // Match the starting screen
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("Home") {
                    HomeScreen()
                }
                composable("Chat") {
                    ChatScreen()
                }
                composable("Notify") {
                    NotificationScreen()
                }
                composable("Profile") {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Home")
    }
}
@Composable
fun ChatScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Chat")
    }
}

@Composable
fun NotificationScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Notification")
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Profile")
    }
}

@Preview(showBackground = true)
@Composable
fun BonBaanAppPreview() {
    BonBaanApp()
}