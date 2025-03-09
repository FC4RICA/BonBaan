package com.fc4rica.bonbaan.ui.auth

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc4rica.bonbaan.ui.components.BonBaanButton
import com.fc4rica.bonbaan.ui.components.TextFieldComponent

@Composable
fun RegisterScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldComponent(label = "อีเมลล์", value = email, onValueChange = { email = it })
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldComponent(label = "รหัสผ่าน", value = password, onValueChange = { password = it })
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldComponent(label = "ยืนยันรหัสผ่าน", value = confirmPassword, onValueChange = { confirmPassword = it })
        Spacer(modifier = Modifier.height(20.dp))
        BonBaanButton(text = "สมัครสมาชิก", onClick = { /* TODO: Handle Register */ })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen()
}