package com.fc4rica.bonbaan.data.repository

import androidx.datastore.core.DataStore
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.data.remote.UserApiService
import com.fc4rica.bonbaan.data.remote.dto.toUser
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.model.request.LoginRequest
import com.fc4rica.bonbaan.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val userApiService: UserApiService,
    private val userPreferences: DataStore<UserPreferences>
) : AuthRepository {
    override suspend fun login(request: LoginRequest): Result<User> {
        return try {
            val loginResponse = userApiService.login(request)

            userPreferences.updateData { prefs ->
                prefs.copy(token = loginResponse.data.token)
            }

            val profileResponse = userApiService.getProfile()
            Result.success(profileResponse.data.toUser())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProfile(): Result<User> {
        return try {
            val response = userApiService.getProfile()
            Result.success(response.data.toUser())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}