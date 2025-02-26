package com.fc4rica.bonbaan.ui.home

import androidx.annotation.StringRes
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
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import com.fc4rica.bonbaan.R

enum class HomeSection(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    FEED(R.string.home_feed, Icons.Outlined.Home, "home/feed"),
    CHAT(R.string.home_chat, Icons.Outlined.MailOutline, "home/chat"),
    NOTIFICATION(R.string.home_notification, Icons.Outlined.Notifications, "home/notification"),
    PROFILE(R.string.home_profile, Icons.Outlined.Person, "home/profile")
}

@Composable
fun BottomNavBar(
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    val currentSection = HomeSection.entries.firstOrNull { it.route == currentRoute }

    NavigationBar {
        HomeSection.entries.forEach { section ->
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