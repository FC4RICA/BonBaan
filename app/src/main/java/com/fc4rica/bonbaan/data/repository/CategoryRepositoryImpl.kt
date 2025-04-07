package com.fc4rica.bonbaan.data.repository

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

            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.map { it.toCategory() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCategory(id: String): Result<Category> {
        return try {
            val response = serviceApiService.getCategory(id)

            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.toCategory())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}