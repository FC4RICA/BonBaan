package com.fc4rica.bonbaan.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.ui.home.profile.ProfileScreen
import com.fc4rica.bonbaan.ui.navigation.Screen

@Composable
fun HomeScreen() {
    val nestedNavController = rememberNavController()
    val currentDestination = nestedNavController.currentBackStackEntryAsState().value?.destination?.route
    val currentSection = HomeSection.fromRoute(currentDestination) ?: HomeSection.Feed

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = currentSection.screen.route,
                navigateToRoute = { sectionRoute  ->
                    nestedNavController.navigate(sectionRoute) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = nestedNavController,
            startDestination = Screen.Feed.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Feed.route) {
                FeedScreen()
            }
            composable(Screen.Chat.route) {
                ChatScreen()
            }
            composable(Screen.Notification.route) {
                NotificationScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

sealed class HomeSection(
    @StringRes val title: Int,
    val icon: ImageVector,
    val screen: Screen
) {
    data object Feed : HomeSection(R.string.home_feed, Icons.Outlined.Home, Screen.Feed)
    data object Chat : HomeSection(R.string.home_chat, Icons.Outlined.MailOutline, Screen.Chat)
    data object Notification : HomeSection(R.string.home_notification, Icons.Outlined.Notifications, Screen.Notification)
    data object Profile : HomeSection(R.string.home_profile, Icons.Outlined.Person, Screen.Profile)

    companion object {
        fun fromRoute(route: String?): HomeSection? = sections.find { it.screen.route == route }

        val sections = listOf(Feed, Chat, Notification, Profile)
    }
}

@Composable
fun BottomNavBar(
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    NavigationBar {
        HomeSection.sections.forEach { section ->
            val selected = currentRoute == section.screen.route

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = section.icon,
                        contentDescription = stringResource(section.title)
                    )
                },
                label = { Text(stringResource(section.title)) },
                selected = selected,
                onClick = { navigateToRoute(section.screen.route) }
            )
        }
    }
}