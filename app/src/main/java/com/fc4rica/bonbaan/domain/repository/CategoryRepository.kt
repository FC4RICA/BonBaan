package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): Result<List<Category>>
}