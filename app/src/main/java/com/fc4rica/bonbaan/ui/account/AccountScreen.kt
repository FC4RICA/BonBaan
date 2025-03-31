package com.fc4rica.bonbaan.ui.account

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant

@Composable
fun AccountScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BonBaanButton(
            text = "Profile Setting",
            onClick = { navController.navigate("profile") },
            variant = ButtonVariant.PRIMARY
        )
        Spacer(modifier = Modifier.height(16.dp))
        BonBaanButton(
            text = "Order Status",
            onClick = { navController.navigate("order") },
            variant = ButtonVariant.PRIMARY
        )
        Spacer(modifier = Modifier.height(16.dp))
        BonBaanButton(
            text = "My Reviews",
            onClick = { navController.navigate("reviews") },
            variant = ButtonVariant.PRIMARY
        )
    }
}