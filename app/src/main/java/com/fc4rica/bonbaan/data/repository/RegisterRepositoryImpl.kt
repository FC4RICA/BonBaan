package com.fc4rica.bonbaan.data.repository

import com.fc4rica.bonbaan.data.remote.UserApiService
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.model.request.RegisterRequest
import com.fc4rica.bonbaan.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterRepositoryImpl(
    private val userApiService: UserApiService
) : RegisterRepository {
    private val _registerRequest = MutableStateFlow(RegisterRequest())
    override val registerRequest: StateFlow<RegisterRequest> = _registerRequest.asStateFlow()

    override fun updateRequest(update: RegisterRequest.() -> RegisterRequest) {
        _registerRequest.update { it.update() }
    }

    override suspend fun register(): Result<User> {
        return Result.success(
            User(
                id = "",
                email = "",
                firstname = "",
                lastname = "",
                username = "",
                phoneNumber = "",
                token = ""
            )
        )
//        return try {
//            val response = userApiService.register(request)
//            Result.success(response)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
    }

    override suspend fun sendOtp(): Result<Unit> {
        return Result.success(Unit)
//        return try {
//            userApiService.sendOtp(email)
//            Result.success(Unit)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
    }
}