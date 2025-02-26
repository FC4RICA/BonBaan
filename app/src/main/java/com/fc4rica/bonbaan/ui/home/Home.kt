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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.ui.MockScreen

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = currentDestination ?: HomeSection.Feed.route,
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
            startDestination = HomeSection.Feed.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(HomeSection.Feed.route) {
                MockScreen("FEED")
            }
            composable(HomeSection.Chat.route) {
                MockScreen("CHAT")
            }
            composable(HomeSection.Notification.route) {
                MockScreen("NOTIFICATION")
            }
            composable(HomeSection.Profile.route) {
                MockScreen("PROFILE")
            }
        }
    }
}

sealed class HomeSection(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    object Feed : HomeSection(R.string.home_feed, Icons.Outlined.Home, "home/feed")
    object Chat : HomeSection(R.string.home_chat, Icons.Outlined.MailOutline, "home/chat")
    object Notification : HomeSection(R.string.home_notification, Icons.Outlined.Notifications, "home/notification")
    object Profile : HomeSection(R.string.home_profile, Icons.Outlined.Person, "home/profile")

    companion object {
        val sections = listOf(Feed, Chat, Notification, Profile)
        fun fromRoute(route: String?): HomeSection? {
            return sections.find { it.route == route }
        }
    }
}

@Composable
fun BottomNavBar(
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    val currentSection = HomeSection.fromRoute(currentRoute)

    NavigationBar {
        HomeSection.sections.forEach { section ->
            val selected = section == currentSection

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = section.icon,
                        contentDescription = stringResource(section.title)
                    )
                },
                label = { Text(stringResource(section.title)) },
                selected = selected,
                onClick = { navigateToRoute(section.route) }
            )
        }
    }
}