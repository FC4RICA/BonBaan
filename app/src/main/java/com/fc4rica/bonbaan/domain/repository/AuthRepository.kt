package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
}