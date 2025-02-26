package com.fc4rica.bonbaan.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fc4rica.bonbaan.ui.theme.BonBaanTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fc4rica.bonbaan.ui.navigation.BonBaanNavHost

@Composable
fun BonBaanApp() {
    BonBaanTheme {
        val navController = rememberNavController()

        BonBaanNavHost(navController = navController)
    }
}



@Composable
fun MockScreen(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun BonBaanAppPreview() {
    BonBaanApp()
}