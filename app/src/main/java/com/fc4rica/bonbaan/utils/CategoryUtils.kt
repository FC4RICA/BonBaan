package com.fc4rica.bonbaan.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Work
import com.fc4rica.bonbaan.domain.model.Category

object CategoryUtils {
    private val categoryIconMap = mapOf(
        "การเรียน" to Icons.Filled.School,
        "การงาน" to Icons.Filled.Work,
        "ความรัก" to Icons.Filled.Favorite,
        "ครอบครัว" to Icons.Filled.FamilyRestroom,
        "สุขภาพ" to Icons.Filled.HealthAndSafety,
        "การเงิน" to Icons.Filled.Money,
        "การค้าขาย" to Icons.Filled.Store
    )

    fun mapCategoriesIcon(categories: List<Category>): List<Category> {
        return categories.map { category ->
            category.copy(icon = categoryIconMap[category.name])
        }
    }
}
