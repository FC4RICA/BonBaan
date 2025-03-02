package com.fc4rica.bonbaan.ui.navigation

sealed class Screen(val route: String) {
    data object Auth : Screen("auth")
    data object Login : Screen("login")
    data object Register : Screen("register")

    data object Onboarding : Screen("onboarding")
    data object Welcome : Screen("welcome")
    data object Interest : Screen("interest")

    data object Home : Screen("home")
    // Bottom Navigation Screens
    data object Feed : Screen("feed")
    data object Chat : Screen("chat")
    data object Notification : Screen("notification")
    data object Profile : Screen("profile")

//    // Nested Screens (Inside Feed)
//    data object Search : Screen("search")
//    data class ServiceDetail(val serviceId: String) : Screen("serviceDetail/{serviceId}") {
//        companion object {
//            fun createRoute(serviceId: String) = "serviceDetail/$serviceId"
//        }
//    }
//
//    // Nested Screens (Inside Profile)
//    data object ServicesStatus : Screen("servicesStatus")
//    data object PreviousServices : Screen("previousServices")
//    data class PreviousServiceDetail(val previousServiceId: String) :
//        Screen("previousServiceDetail/{previousServiceId}") {
//        companion object {
//            fun createRoute(previousServiceId: String) = "previousServiceDetail/$previousServiceId"
//        }
//    }
//
//    data object ProfileSetting : Screen("profileSetting")
}