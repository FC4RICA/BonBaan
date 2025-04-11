package com.fc4rica.bonbaan.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fc4rica.bonbaan.ui.theme.BonBaanTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.fc4rica.bonbaan.ui.navigation.BonBaanNavHost
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

@Composable
fun BonBaanApp(mainViewModel: MainViewModel = koinViewModel()) {
    BonBaanTheme {
        KoinContext {
            val navController = rememberNavController()
            val isAuthenticated by mainViewModel.isAuthenticated.collectAsState()
            val hasSelectedInterests by mainViewModel.hasSelectedInterests.collectAsState()

            if (isAuthenticated == null || hasSelectedInterests == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(64.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                return@KoinContext
            }

            BonBaanNavHost(navController, isAuthenticated!!, hasSelectedInterests!!)
        }
    }
}


@Composable
fun MockScreen(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text)
    }
}
