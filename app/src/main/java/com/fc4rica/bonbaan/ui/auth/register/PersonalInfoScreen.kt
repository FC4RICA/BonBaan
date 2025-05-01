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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fc4rica.bonbaan.R
import com.fc4rica.bonbaan.di.appModule
import com.fc4rica.bonbaan.di.networkModule
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.BonBaanTextField
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@Composable
fun PersonalInfoScreen(
    navigateToSetupPassword: () -> Unit,
    viewModel: PersonalInfoViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val registerRequest by viewModel.registerRequest.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
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
            Text(text = "ข้อมูลส่วนตัว", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "ใส่ชื่อ และเบอร์โทรศัพท์ของคุณ",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            BonBaanTextField(
                label = "ชื่อจริง นามสกุล",
                value = state.name,
                onValueChange = { viewModel.updateName(it) })
            if (!state.nameError.isNullOrEmpty()) {
                Text(
                    text = state.nameError ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            BonBaanTextField(
                label = "เบอร์โทรศัพท์",
                value = registerRequest.phone,
                onValueChange = { viewModel.updatePhone(it) })
            if (!state.phoneError.isNullOrEmpty()) {
                Text(
                    text = state.phoneError ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            BonBaanTextField(
                label = "ชื่อบัญชี",
                value = registerRequest.username,
                onValueChange = { viewModel.updateUsername(it) })
            if (!state.usernameError.isNullOrEmpty()) {
                Text(
                    text = state.usernameError ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            BonBaanButton(
                text = "ถัดไป",
                onClick = { if (viewModel.submitPersonalInfo()) navigateToSetupPassword() },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPersonalInfoScreen() {
    KoinApplication(application = {
        modules(appModule, networkModule)
    }) {
        PersonalInfoScreen(
            navigateToSetupPassword = {}
        )
    }
}