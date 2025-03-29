package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.model.request.LoginRequest

interface AuthRepository {
    suspend fun login(request: LoginRequest): Result<User>
    suspend fun getProfile(): Result<User>
}