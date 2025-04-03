package com.fc4rica.bonbaan.data.repository

import android.util.Log
import com.fc4rica.bonbaan.data.remote.ServiceApiService
import com.fc4rica.bonbaan.data.remote.dto.toCategory
import com.fc4rica.bonbaan.domain.model.Category
import com.fc4rica.bonbaan.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val serviceApiService: ServiceApiService
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            val response = serviceApiService.getCategories()
            Log.d("CategoryRepositoryImpl", "Response: $response")

            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.map { it.toCategory() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}