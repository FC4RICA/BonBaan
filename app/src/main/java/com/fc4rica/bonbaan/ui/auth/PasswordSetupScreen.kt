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
import com.fc4rica.bonbaan.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun PasswordSetupScreen(
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
            Text(text = "สร้างรหัสผ่าน", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "สร้างรหัสผ่านที่แข็งแรงเพื่อความปลอดภัยของบัญชีคุณ",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            BonBaanTextField(
                label = "รหัสผ่าน",
                value = state.password,
                onValueChange = { viewModel.updateField("password", it) },
                isPassword = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            BonBaanTextField(
                label = "ยืนยันรหัสผ่าน",
                value = state.confirmPassword,
                onValueChange = { viewModel.updateField("confirmPassword", it) },
                isPassword = true
            )
            Spacer(modifier = Modifier.height(24.dp))
            BonBaanButton(
                text = "ยืนยัน",
                onClick = { navController.navigate(Screen.EmailVerification.route) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPasswordSetupScreen() {
    val navController = rememberNavController()
    PasswordSetupScreen(navController)
}