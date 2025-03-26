package com.fc4rica.bonbaan.data.remote

import com.fc4rica.bonbaan.data.remote.dto.AuthResponse
import com.fc4rica.bonbaan.domain.model.request.LoginRequest
import com.fc4rica.bonbaan.domain.model.request.OtpRequest
import com.fc4rica.bonbaan.domain.model.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface UserApiService {
    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("users/register")
    suspend fun register(@Body request: RegisterRequest): Response<Unit>

    @POST("users/send-otp")
    suspend fun sendOtp(@Body request: OtpRequest): Response<Unit>
}