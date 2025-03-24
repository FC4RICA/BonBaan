package com.fc4rica.bonbaan.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant
import com.fc4rica.bonbaan.ui.components.OtpInputField
import com.fc4rica.bonbaan.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun EmailVerificationScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "App Logo",
                modifier = Modifier
                    .height(72.dp)
                    .width(216.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "ใส่รหัสยืนยัน", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "เราได้ส่งรหัสยืนยันไปที่อีเมลของคุณ นำรหัสมาใส่เพื่อยืนยันอีเมลของคณ",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            OtpInputField(value = state.code, onValueChange = { viewModel.updateField("code", it) }, length = 6)
            Spacer(modifier = Modifier.height(24.dp))
            BonBaanButton(
                text = "ถัดไป",
                onClick = { navController.navigate(Screen.PersonalInfo.route) },
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.isCodeValid
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "ไม่ได้รับรหัสผ่าน?", style = MaterialTheme.typography.bodyMedium)
            BonBaanButton(
                text = "ส่งรหัสยืนยันอีกรอบ",
                onClick = { navController.navigate(Screen.Onboarding.route) },
                variant = ButtonVariant.TEXT
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmailVerificationScreen() {
    val navController = rememberNavController()
    EmailVerificationScreen(navController)
}