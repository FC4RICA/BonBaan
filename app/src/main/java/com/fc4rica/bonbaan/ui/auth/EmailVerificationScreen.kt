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
import com.fc4rica.bonbaan.ui.components.BonBaanTextField
import com.fc4rica.bonbaan.ui.components.ButtonVariant

@Composable
fun EmailVerificationScreen(navController: NavHostController) {
    var otp by remember { mutableStateOf("") }

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
            Text(text = "ใส่รหัสยืนยัน", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "เราได้ส่งรหัสยืนยันไปที่อีเมลของคุณ นำรหัสมาใส่เพื่อยืนยันอีเมลของคณ",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            BonBaanTextField(label = "รหัส", value = otp, onValueChange = { otp = it })
            Spacer(modifier = Modifier.height(24.dp))
            BonBaanButton(
                text = "ถัดไป",
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                isEnabled = otp.length == 5
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "ไม่ได้รับรหัสผ่าน?", style = MaterialTheme.typography.bodyMedium)
            BonBaanButton(text = "ส่งรหัสยืนยันอีกรอบ", onClick = { }, variant = ButtonVariant.TEXT)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmailVerificationScreen() {
    val navController = rememberNavController()
    EmailVerificationScreen(navController)
}