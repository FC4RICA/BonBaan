package com.fc4rica.bonbaan.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.fc4rica.bonbaan.di.appModule
import com.fc4rica.bonbaan.di.networkModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Keep splash if auth state is not yet determined
        installSplashScreen().setKeepOnScreenCondition {
            val mainViewModel: MainViewModel by inject()
            Log.d("MainActivity", "onCreate: ${mainViewModel.isAuthenticated.value}")
            mainViewModel.isAuthenticated.value == null
        }
        // Dependency injection with koin
        startKoin{
            androidContext(this@MainActivity)
            modules(appModule, networkModule)
        }
        setContent {
            BonBaanApp()
        }
    }
}