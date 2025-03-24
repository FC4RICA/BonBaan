package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.model.request.RegisterRequest

interface UserRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(request: RegisterRequest): Result<User>
    suspend fun sendOtp(email: String): Result<Unit>
}