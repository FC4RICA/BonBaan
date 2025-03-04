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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.ui.home.feed.FeedScreen
import com.fc4rica.bonbaan.ui.home.feed.SearchScreen
import com.fc4rica.bonbaan.ui.home.feed.ServiceDetailScreen
import com.fc4rica.bonbaan.ui.home.profile.MyReviewsScreen
import com.fc4rica.bonbaan.ui.home.profile.OrderStatusDetailScreen
import com.fc4rica.bonbaan.ui.home.profile.OrdersStatusScreen
import com.fc4rica.bonbaan.ui.home.profile.PreviousVowDetailScreen
import com.fc4rica.bonbaan.ui.home.profile.PreviousVowsScreen
import com.fc4rica.bonbaan.ui.home.profile.ProfileScreen
import com.fc4rica.bonbaan.ui.home.profile.ProfileSettingScreen
import com.fc4rica.bonbaan.ui.navigation.Screen

@Composable
fun HomeScreen() {
    val nestedNavController = rememberNavController()
    val currentDestination =
        nestedNavController.currentBackStackEntryAsState().value?.destination?.route
    val currentSection = HomeSection.fromRoute(currentDestination) ?: HomeSection.Feed

    Scaffold(
        bottomBar = {
            val shouldShowBottomBar =
                HomeSection.sections.any { it.screen.route == currentDestination }
            if (shouldShowBottomBar) {
                BottomNavBar(
                    currentRoute = currentSection.screen.route,
                    navigateToRoute = { sectionRoute ->
                        nestedNavController.navigate(sectionRoute) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = nestedNavController,
            startDestination = Screen.Feed.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            homeGraph(nestedNavController)
        }
    }
}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    // Bottom Navigation Screens
    composable(Screen.Feed.route) {
        FeedScreen(navController)
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

    //  Nested Feed Screens
    composable(Screen.Search.route) {
        SearchScreen()
    }
    composable(
        route = Screen.ServiceDetail("{serviceId}").route,
        arguments = listOf(navArgument("serviceId") { type = NavType.StringType })
    ) { backStackEntry ->
        val serviceId = backStackEntry.arguments?.getString("serviceId") ?: ""
        ServiceDetailScreen(serviceId)
    }

    // Nested Profile Screens
    composable(Screen.OrdersStatus.route) {
        OrdersStatusScreen()
    }
    composable(
        route = Screen.OrderStatusDetail("{orderId}").route,
        arguments = listOf(navArgument("orderId") { type = NavType.StringType })
    ) { backStackEntry ->
        val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
        OrderStatusDetailScreen(orderId)
    }
    composable(Screen.PreviousVows.route) {
        PreviousVowsScreen()
    }
    composable(
        route = Screen.PreviousVowDetail("{vowId}").route,
        arguments = listOf(navArgument("vowId") { type = NavType.StringType })
    ) { backStackEntry ->
        val vowId = backStackEntry.arguments?.getString("vowId") ?: ""
        PreviousVowDetailScreen(vowId)
    }
    composable(Screen.MyReviews.route) { MyReviewsScreen() }
    composable(Screen.ProfileSetting.route) { ProfileSettingScreen() }
}

sealed class HomeSection(
    @StringRes val title: Int,
    val icon: ImageVector,
    val screen: Screen
) {
    data object Feed : HomeSection(R.string.home_feed, Icons.Outlined.Home, Screen.Feed)
    data object Chat : HomeSection(R.string.home_chat, Icons.Outlined.MailOutline, Screen.Chat)
    data object Notification :
        HomeSection(R.string.home_notification, Icons.Outlined.Notifications, Screen.Notification)

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