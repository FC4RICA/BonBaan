package com.fc4rica.bonbaan.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fc4rica.bonbaan.ui.theme.BonBaanTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun BonBaanApp() {
    BonBaanTheme {
        var username by remember { mutableStateOf("") }
        Column(
            modifier = Modifier.fillMaxSize().padding(30.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().border(1.dp, androidx.compose.ui.graphics.Color.Cyan).padding(4.dp),
            ) {
                OutlinedTextField(
                    value = username,
                    label = { Text("Enter your Username") },
                    onValueChange = { text ->
                        username = text
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}