package com.fc4rica.bonbaan.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.data.local.toUser
import com.fc4rica.bonbaan.data.remote.UserApiService
import com.fc4rica.bonbaan.data.remote.dto.toUser
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.model.request.LoginRequest
import com.fc4rica.bonbaan.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first

class AuthRepositoryImpl(
    private val userApiService: UserApiService,
    private val userPreferences: DataStore<UserPreferences>,
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
            userPreferences.updateData { prefs ->
                prefs.copy(
                    id = user.id,
                    email = user.email,
                    firstname = user.firstname,
                    lastname = user.lastname,
                    username = user.username,
                    phone = user.phone
                )
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Log.d("AuthRepositoryImpl", "login-error: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getProfile(useLocalStorage: Boolean): Result<User> {
        return try {
            if (useLocalStorage) {
                // fetch user from local storage
                val userPref = userPreferences.data.first()
                if (userPref.token == null) {
                    return Result.failure(Exception("User not found"))
                }
                val user = userPref.toUser()
                if (user != null) {
                    return Result.success(user)
                }
            }

            // if not found in local storage, fetch from server
            val response = userApiService.getProfile()

            if (response.error != null || response.data == null) {
                return Result.failure(Exception("Invalid or expired token"))
            }

            val user = response.data.toUser()
            userPreferences.updateData { prefs ->
                prefs.copy(
                    id = user.id,
                    email = user.email,
                    firstname = user.firstname,
                    lastname = user.lastname,
                    username = user.username,
                    phone = user.phone
                )
            }

            Result.success(user)
        } catch (e: Exception) {
            Log.d("AuthRepositoryImpl", "getProfile-error: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        try {
            userPreferences.updateData { UserPreferences() }
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}