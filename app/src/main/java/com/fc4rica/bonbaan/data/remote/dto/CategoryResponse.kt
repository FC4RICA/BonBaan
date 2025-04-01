package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.Category

data class CategoryResponse(
    val id: String,
    val name: String
)

fun CategoryResponse.toCategory(): Category {
    return Category(
        id = id,
        name = name
    )
}