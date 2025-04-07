package com.fc4rica.bonbaan.ui.navigation

sealed class Screen(val route: String) {
    data object Auth : Screen("auth")
    data object Login : Screen("login")

    data object Register : Screen("register")
    data object InputEmail : Screen("inputEmail")
    data object EmailVerification : Screen("emailVerification")
    data object PersonalInfo : Screen("personalInfo")
    data object PasswordSetup : Screen("passwordSetup")

    data object Onboarding : Screen("onboarding")
    data object Welcome : Screen("welcome")
    data object Interest : Screen("interest")

    data object Home : Screen("home")

    // Bottom Navigation Screens
    data object Feed : Screen("feed")
    data object VowRecord : Screen("vowRecord")
    data object Notification : Screen("notification")
    data object Profile : Screen("profile")

    // Nested Screens (Inside Feed)
    data object Search : Screen("search")
    data object CategorizeService : Screen("categorizeService/{categoryId}") {
        fun createRoute(categoryId: String) = "categorizeService/$categoryId"
    }
    data object FilteredService : Screen("filteredService/{query}") {
        fun createRoute(query: String) = "filteredService/$query"
    }

    // Service Screen
    data object ServiceDetail : Screen("serviceDetail/{serviceId}") {
        fun createRoute(serviceId: String) = "serviceDetail/$serviceId"
    }
    data object ServiceReview : Screen("serviceReview/{serviceId}") {
        fun createRoute(serviceId: String) = "serviceReview/$serviceId"
    }
    data object ServicePackage : Screen("servicePackage/{serviceId}") {
        fun createRoute(serviceId: String) = "servicePackage/$serviceId"
    }

    // Nested Screen (Inside VowRecord)
    data object VowRecordDetail : Screen("vowRecordDetail/{vowRecordId}") {
        fun createRoute(vowRecordId: String) = "vowRecordDetail/$vowRecordId"
    }

    // Nested Screens (Inside Profile)
    data object OrdersStatus : Screen("ordersStatus")
    data object OrderStatusDetail : Screen("orderStatusDetail/{orderId}") {
        fun createRoute(orderId: String) = "orderStatusDetail/$orderId"
    }
    data object MyReviews : Screen("myReviews")
}