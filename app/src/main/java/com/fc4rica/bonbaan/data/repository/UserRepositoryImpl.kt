package com.fc4rica.bonbaan.data.repository

import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.repository.UserRepository

class UserRepositoryImpl(
//    private val apiService: ApiService
) : UserRepository {
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