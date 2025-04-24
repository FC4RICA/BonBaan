package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.model.request.LoginRequest

interface AuthRepository {
    suspend fun login(request: LoginRequest): Result<Unit>
    suspend fun getProfile(useLocalStorage: Boolean = true): Result<User>
    suspend fun logout(): Result<Unit>
}