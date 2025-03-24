package com.fc4rica.bonbaan.domain.usecase.user

import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {
    suspend fun excute(email: String, password: String): Result<User> {
        return userRepository.login(email, password)
    }
}