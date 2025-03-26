package com.fc4rica.bonbaan.ui.auth

import androidx.compose.animation.core.animateFloatAsState
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
import com.fc4rica.bonbaan.di.previewModule
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.BonBaanTextField
import com.fc4rica.bonbaan.ui.components.ButtonVariant
import com.fc4rica.bonbaan.ui.utils.rememberImeState
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@Composable
fun InputEmailScreen(
    navigateToLogin: () -> Unit,
    navigateToPersonalInfo: () -> Unit,
    viewModel: InputEmailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val registerRequest by viewModel.registerRequest.collectAsState()
    val isImeVisable = rememberImeState()

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
            val animatedUpperSectionRatio by animateFloatAsState(targetValue = if (isImeVisable) 0f else 0.3f)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(animatedUpperSectionRatio)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo1),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp)
                )
            }
            Text(text = "สร้างบัญชีใหม่", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "เราจะส่งรหัสยืนยันผ่านอีเมลของคุณ",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            BonBaanTextField(
                label = "อีเมล",
                value = registerRequest.email,
                onValueChange = { viewModel.updateEmail(it) })
            if (!state.emailError.isNullOrEmpty()) {
                Text(
                    text = state.emailError ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            BonBaanButton(
                text = "ถัดไป",
                onClick = { if (viewModel.submitEmail()) navigateToPersonalInfo() },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "มีบัญชีอยู่แล้ว?", style = MaterialTheme.typography.bodyMedium)
            BonBaanButton(
                text = "เข้าสู่ระบบ",
                onClick = { navigateToLogin() },
                variant = ButtonVariant.TEXT
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInputEmailScreen() {
    KoinApplication(application = {
        modules(previewModule)
    }) {
        InputEmailScreen(
            navigateToLogin = {},
            navigateToPersonalInfo = {}
        )
    }
}