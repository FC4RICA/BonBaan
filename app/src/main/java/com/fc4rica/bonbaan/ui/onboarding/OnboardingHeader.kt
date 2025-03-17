package com.fc4rica.bonbaan.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fc4rica.bonbaan.R

@Composable
fun OnboardingHeader(title: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = "Bonbaan Logo",
            tint = Color.Unspecified,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(subtitle, fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(24.dp))
    }
}