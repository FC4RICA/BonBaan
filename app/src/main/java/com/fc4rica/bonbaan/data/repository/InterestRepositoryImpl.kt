package com.fc4rica.bonbaan.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.data.remote.UserApiService
import com.fc4rica.bonbaan.data.remote.dto.toCategory
import com.fc4rica.bonbaan.domain.model.Category
import com.fc4rica.bonbaan.domain.model.request.InterestRequest
import com.fc4rica.bonbaan.domain.repository.InterestRepository
import kotlinx.coroutines.flow.first

class InterestRepositoryImpl(
    private val userApiService: UserApiService,
    private val userPreferences: DataStore<UserPreferences>,
) : InterestRepository {
    override suspend fun getInterests(): Result<List<Category>> {
        return try {
            val userId = userPreferences.data.first().id
                ?: return Result.failure(Exception("User ID not found"))
            Log.d("InterestRepositoryImpl", "User ID: $userId")
            val response = userApiService.getInterests(userId)
            Log.d("InterestRepositoryImpl", "Response: $response")

            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.map { it.toCategory() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addInterest(interests: InterestRequest): Result<Unit> {
        Log.d("InterestRepositoryImpl", "addInterest: $interests")
        return try {
            val userId = userPreferences.data.first().id
            Log.d("InterestRepositoryImpl", "User ID: $userId")
            val response = userApiService.addInterest(userId!!, interests)
            Log.d("InterestRepositoryImpl", "Response: $response")
            if (response.error != null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Log.d("InterestRepositoryImpl", "Exception: $e")
            Result.failure(e)
        }
    }
}