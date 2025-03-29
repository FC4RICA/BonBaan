package com.fc4rica.bonbaan.data.repository

import android.util.Log
import com.fc4rica.bonbaan.data.remote.UserApiService
import com.fc4rica.bonbaan.domain.model.request.OtpRequest
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

    override suspend fun register(): Result<Unit> {
        Log.d("RegisterRepositoryImpl", "register: ${registerRequest.value}")
        return try {
            val request = registerRequest.value
            val response = userApiService.register(request)
            Log.d("RegisterRepositoryImpl", "registerResponse: $response")

            if (response.error != null) {

                return Result.failure(Exception(response.error))
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Log.d("RegisterRepositoryImpl", "registerException: $e")
            Result.failure(e)
        }
    }

    override suspend fun sendOtp(): Result<Unit> {
        Log.d("RegisterRepositoryImpl", "sendOtp: ${registerRequest.value.email}")
        return try {
            val request = OtpRequest(registerRequest.value.email)
            val response = userApiService.sendOtp(request)
            Log.d("RegisterRepositoryImpl", "sendOtpResponse: $response")

            if (response.error != null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Log.d("RegisterRepositoryImpl", "sendOtpException: $e")
            Result.failure(e)
        }
    }
}