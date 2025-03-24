package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.request.RegisterRequest
import com.fc4rica.bonbaan.domain.model.User

interface UserRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(request: RegisterRequest): Result<User>
    suspend fun otp(email: String): Result<Unit>
}