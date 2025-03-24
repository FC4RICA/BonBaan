package com.fc4rica.bonbaan.domain.usecase.user

import com.fc4rica.bonbaan.domain.model.request.RegisterRequest
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.repository.UserRepository
import com.fc4rica.bonbaan.ui.auth.RegisterUiState

class RegisterUseCase(private val userRepository: UserRepository) {
    suspend fun execute(state: RegisterUiState): Result<User> {
        val nameParts = state.name.trim().split(" ", limit = 2)
        val firstname = nameParts.getOrNull(0) ?: ""
        val lastname = nameParts.getOrNull(1) ?: ""

        val request = RegisterRequest(
            username = state.username,
            firstname = firstname,
            lastname = lastname,
            email = state.email,
            phone = state.phone,
            password = state.password,
            code = state.code
        )

        return userRepository.register(request)
    }
}