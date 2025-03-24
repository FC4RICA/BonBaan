package com.fc4rica.bonbaan.domain.usecase.user

import com.fc4rica.bonbaan.domain.repository.UserRepository

class OtpUseCase(private val userRepository: UserRepository) {
    suspend fun execute(email: String): Result<Unit> {
        return userRepository.otp(email)
    }
}