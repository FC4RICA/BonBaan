package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.request.RegisterRequest
import kotlinx.coroutines.flow.StateFlow

interface RegisterRepository {
    val registerRequest: StateFlow<RegisterRequest>
    fun updateRequest(update: RegisterRequest.() -> RegisterRequest)
    suspend fun register(): Result<Unit>
    suspend fun sendOtp(): Result<Unit>
}