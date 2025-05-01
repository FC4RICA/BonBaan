package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("ID")
    val id: String,
    val name: String
)

fun CategoryResponse.toCategory(
    mapCategoryId: Boolean = true,
): Category {
    return Category(
        id = if (mapCategoryId) id else "",
        name = name
    )
}