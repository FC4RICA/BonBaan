package com.fc4rica.bonbaan.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.fc4rica.bonbaan.data.local.SecurePreferences
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.data.remote.UserApiService
import com.fc4rica.bonbaan.data.remote.dto.toUser
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.model.request.LoginRequest
import com.fc4rica.bonbaan.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val userApiService: UserApiService,
    private val userPreferences: DataStore<UserPreferences>,
    private val securePreferences: SecurePreferences
) : AuthRepository {
    override suspend fun login(request: LoginRequest): Result<Unit> {
        return try {
            Log.d("AuthRepositoryImpl", "login-request: $request")
            val loginResponse = userApiService.login(request)
            Log.d("AuthRepositoryImpl", "login-response: $loginResponse")

            if (loginResponse.data == null || loginResponse.data.token.isEmpty()) {
                return Result.failure(Exception("Invalid credentials"))
            }
            // save token to local storage
            userPreferences.updateData { prefs ->
                prefs.copy(token = loginResponse.data.token)
            }

            // fetch user profile
            val profileResponse = userApiService.getProfile()
            if (profileResponse.error != null || profileResponse.data == null) {
                return Result.failure(Exception("Invalid or expired token"))
            }
            Log.d("AuthRepositoryImpl", "profile-response: $profileResponse")
            val user = profileResponse.data.toUser()
            // save user to local storage
            securePreferences.saveUserData(user)

            Result.success(Unit)
        } catch (e: Exception) {
            Log.d("AuthRepositoryImpl", "login-error: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getProfile(): Result<User> {
        return try {
            // fetch user from local storage
            securePreferences.getUserData()?.let { cachedUser ->
                return Result.success(cachedUser)
            }

            // if not found in local storage, fetch from server
            val response = userApiService.getProfile()

            if (response.error != null || response.data == null) {
                return Result.failure(Exception("Invalid or expired token"))
            }

            val user = response.data.toUser()
            securePreferences.saveUserData(user)
            Result.success(user)
        } catch (e: Exception) {
            Log.d("AuthRepositoryImpl", "getProfile-error: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        try {
            userPreferences.updateData { UserPreferences() }
            securePreferences.clearUserData()
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}