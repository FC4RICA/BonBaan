package com.fc4rica.bonbaan.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import com.fc4rica.bonbaan.ui.components.ButtonVariant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.BonBaanTextField

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onRegisterClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BonBaanTextField(
            label = "อีเมล", value = email,
            onValueChange = { email = it },

            )

        Spacer(modifier = Modifier.height(16.dp))

        BonBaanTextField(
            label = "รหัสผ่าน",
            value = password,
            onValueChange = { password = it },

        )

        Spacer(modifier = Modifier.height(24.dp))
        BonBaanButton(
            text = "เข้าสู่ระบบ",
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            variant = ButtonVariant.TEXT
        )
    }

    }

//@Preview(showBackground = true)
//@Composable
//fun PreviewScreen() {
//    LoginScreen()
//}