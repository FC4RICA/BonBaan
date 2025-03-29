package com.fc4rica.bonbaan.ui.auth.register

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
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.di.appModule
import com.fc4rica.bonbaan.di.networkModule
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.ButtonVariant
import com.fc4rica.bonbaan.ui.components.OtpInputField
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@Composable
fun EmailVerificationScreen(
    navigateToLogin: () -> Unit,
    viewModel: EmailVerificationViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val otpCooldown by viewModel.otpCooldown.collectAsState()
    val registerRequest by viewModel.registerRequest.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEnterEmailVerificationScreen()
    }

    LaunchedEffect(Unit) {
        viewModel.navigateToLogin.collect {
            navigateToLogin()
        }
    }

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
            OtpInputField(value = registerRequest.code, onValueChange = { viewModel.updateOtp(it) }, length = 6)
            if (state.isCodeValid == false) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "รหัสไม่ถูกต้องกรุณาลองใหม่อีกครั้ง",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            BonBaanButton(
                text = "ถัดไป",
                onClick = { viewModel.registerUser() },
                modifier = Modifier.fillMaxWidth(),
                isEnabled = registerRequest.code.length == 6
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "ไม่ได้รับรหัสผ่าน?", style = MaterialTheme.typography.bodyMedium)
            BonBaanButton(
                text = if (otpCooldown > 0) "ส่งอีกครั้งใน $otpCooldown วินาที" else "ส่งรหัสยืนยันอีกรอบ",
                onClick = { if (otpCooldown <= 0) viewModel.sendOTP() },
                variant = ButtonVariant.TEXT
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmailVerificationScreen() {
    KoinApplication(application = {
        modules(appModule, networkModule)
    }) {
        EmailVerificationScreen(
            navigateToLogin = {}
        )
    }
}