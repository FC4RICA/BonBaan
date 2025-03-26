package com.fc4rica.bonbaan.data.repository

import android.content.SharedPreferences
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.repository.AuthRepository

class AuthRepositoryImpl(
//    private val apiService: ApiService
    private val sharedPreferences: SharedPreferences
) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<User> {
        return Result.success(User(
            id = "",
            email = "",
            firstname = "",
            lastname = "",
            username = "",
            phoneNumber = "",
            token = ""
        ))
//        return try {
//            val response = apiService.login(email, password)
//            Result.success(response)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
    }
}