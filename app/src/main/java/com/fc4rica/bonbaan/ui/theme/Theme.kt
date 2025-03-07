package com.fc4rica.bonbaan.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF5E17EB),
    secondary = Color(0xFFF9B533),
    tertiary = Color(0xFF5E17EB),

    background = Color(0xFFFFFFFF),
    surface = Color(0xFFEBEBEB),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF262626),
    onSurface = Color(0xFF262626),
)

@Composable
fun BonBaanTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}