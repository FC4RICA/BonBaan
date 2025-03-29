package com.fc4rica.bonbaan.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.fc4rica.bonbaan.di.appModule
import com.fc4rica.bonbaan.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        startKoin{
            androidContext(this@MainActivity)
            modules(appModule, networkModule)
        }
        setContent {
            BonBaanApp()
        }
    }
}