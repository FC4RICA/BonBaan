package com.fc4rica.bonbaan.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val id: String,
    val name: String,
    val icon: ImageVector? = null
)
