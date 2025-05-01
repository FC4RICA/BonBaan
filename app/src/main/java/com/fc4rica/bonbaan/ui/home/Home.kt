package com.fc4rica.bonbaan.ui.home

import ProfileScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.ui.home.feed.CategorizeServiceScreen
import com.fc4rica.bonbaan.ui.home.feed.FeedScreen
import com.fc4rica.bonbaan.ui.home.feed.FilteredServiceScreen
import com.fc4rica.bonbaan.ui.home.feed.SearchScreen
import com.fc4rica.bonbaan.ui.home.notification.NotificationScreen
import com.fc4rica.bonbaan.ui.home.service.OrderScreen
import com.fc4rica.bonbaan.ui.home.service.OrderStatusDetailScreen
import com.fc4rica.bonbaan.ui.home.service.OrderSummaryScreen
import com.fc4rica.bonbaan.ui.home.service.OrdersStatusScreen
import com.fc4rica.bonbaan.ui.home.service.PaymentScreen
import com.fc4rica.bonbaan.ui.home.service.ServiceDetailScreen
import com.fc4rica.bonbaan.ui.home.service.ServiceReviewScreen
import com.fc4rica.bonbaan.ui.home.service.VowRecordDetailScreen
import com.fc4rica.bonbaan.ui.home.service.VowRecordScreen
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
                BonBaanBottomNavBar(
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

// Navigation Graph
fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    // Bottom Navigation Screens
    composable(Screen.Feed.route) {
        FeedScreen(
            onClickSearch = { navController.navigate(Screen.Search.createRoute("")) },
            onClickCategory = { navController.navigate(Screen.CategorizeService.createRoute(it)) },
            onClickService = { navController.navigate(Screen.ServiceDetail.createRoute(it)) }
        )
    }
    composable(Screen.VowRecord.route) {
        VowRecordScreen()
    }
    composable(Screen.Notification.route) {
        NotificationScreen(
            onClickNotification = { navController.navigate(Screen.OrderStatusDetail.createRoute(it)) }
        )
    }
    composable(Screen.Profile.route) {
        ProfileScreen(
            onClickProfile = { navController.navigate(Screen.OrdersStatus.route) },
            onClickOrderStatuses = { navController.navigate(Screen.OrdersStatus.route) },
            onClickOrderStatus = { navController.navigate(Screen.OrderStatusDetail.createRoute(it)) },
            onClickReviews = { navController.navigate(Screen.MyReviews.route) }
        )
    }

    //  Nested Feed Screens
    composable(Screen.CategorizeService.route) {
        CategorizeServiceScreen()
    }
    composable(
        route = Screen.FilteredService.route,
        arguments = listOf(navArgument("query") { type = NavType.StringType })
    ) {
        FilteredServiceScreen(
            onBackClick = { navController.popBackStack() },
            onServiceClick = { navController.navigate(Screen.ServiceDetail.createRoute(it)) },
            onSearching = { navController.navigate(Screen.Search.createRoute(it)) }
        )
    }
    composable(Screen.Search.route) {
        SearchScreen(
            onBackClick = { navController.popBackStack() },
            onSearching = { navController.navigate(Screen.FilteredService.createRoute(it)) }
        )
    }
    composable(
        route = Screen.ServiceDetail.route,
        arguments = listOf(navArgument("serviceId") { type = NavType.StringType })
    ) {
        ServiceDetailScreen(
            onOrderSuccess = { navController.navigate(Screen.OrderSummary.route) },
            onBack = { navController.popBackStack() }
        )
    }
    composable(
        route = Screen.ServiceReview.route,
        arguments = listOf(navArgument("serviceId") { type = NavType.StringType })
    ) {
        ServiceReviewScreen()
    }

    // Order Flow Screens
    composable(Screen.Order.route) {
        OrderScreen(
            onSubmitOrder = { navController.navigate(Screen.OrderSummary.route) },
            onBackClick = { navController.popBackStack() }
        )
    }
    composable(Screen.OrderSummary.route) {
        OrderSummaryScreen(
            onBackClick = { navController.popBackStack() },
            onPayingOrder = { navController.navigate(Screen.Payment.createRoute(it)) },
            onCustomOrder = { navController.navigate(Screen.OrderStatusDetail.createRoute(it)) }
        )
    }
    composable(
        route = Screen.Payment.route,
        arguments = listOf(navArgument("orderId") { type = NavType.StringType })
    ) {
        PaymentScreen(
            onBackClick = { navController.navigate(Screen.Feed.route) },
            onCompleted = { navController.navigate(Screen.OrderStatusDetail.createRoute(it)) }
        )
    }

    // Nested VowRecord Screen
    composable(
        route = Screen.VowRecordDetail.route,
        arguments = listOf(navArgument("vowRecordId") { type = NavType.StringType })
    ) {
        VowRecordDetailScreen()
    }

    // Nested Profile Screens
    composable(Screen.OrdersStatus.route) {
        OrdersStatusScreen(
            onClickOrderDetail = { navController.navigate(Screen.OrderStatusDetail.createRoute(it)) },
            onClickServiceDetail = { navController.navigate(Screen.ServiceDetail.createRoute(it)) },
            onClickPayment = { navController.navigate(Screen.Payment.createRoute(it)) },
            onClickReview = { navController.navigate(Screen.ServiceReview.createRoute(it)) }
        )
    }
    composable(
        route = Screen.OrderStatusDetail.route,
        arguments = listOf(navArgument("orderId") { type = NavType.StringType })
    ) {
        OrderStatusDetailScreen()
    }
    // composable(Screen.MyReviews.route) { MyReviewsScreen() }
}

sealed class HomeSection(
    @StringRes val title: Int,
    val icon: ImageVector,
    val screen: Screen
) {
    data object Feed : HomeSection(R.string.home_feed, Icons.Filled.Home, Screen.Feed)
    data object VowRecord : HomeSection(
        R.string.home_vow_record,
        Icons.AutoMirrored.Filled.StickyNote2, Screen.VowRecord
    )

    data object Notification :
        HomeSection(R.string.home_notification, Icons.Filled.Notifications, Screen.Notification)

    data object Profile : HomeSection(R.string.home_profile, Icons.Filled.Person, Screen.Profile)

    companion object {
        fun fromRoute(route: String?): HomeSection? = sections.find { it.screen.route == route }

        val sections = listOf(Feed, VowRecord, Notification, Profile)
    }
}

@Composable
fun BonBaanBottomNavBar(
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp)
    ) {
        HomeSection.sections.forEach { section ->
            val selected = currentRoute == section.screen.route

            NavigationBarItem(
                modifier = Modifier.fillMaxHeight(),
                icon = {
                    Icon(
                        imageVector = section.icon,
                        contentDescription = stringResource(section.title),
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(section.title),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                selected = selected,
                onClick = { navigateToRoute(section.screen.route) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BonBaanBottomNavBarPreview() {
    BonBaanBottomNavBar(
        currentRoute = Screen.Feed.route,
        navigateToRoute = {}
    )
}