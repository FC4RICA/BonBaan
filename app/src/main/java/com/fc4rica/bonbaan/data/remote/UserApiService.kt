package com.fc4rica.bonbaan.data.remote

import com.fc4rica.bonbaan.data.remote.dto.ApiResponse
import com.fc4rica.bonbaan.data.remote.dto.AuthResponse
import com.fc4rica.bonbaan.data.remote.dto.UserResponse
import com.fc4rica.bonbaan.domain.model.request.LoginRequest
import com.fc4rica.bonbaan.domain.model.request.OtpRequest
import com.fc4rica.bonbaan.domain.model.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<AuthResponse>

    @GET("users/me")
    suspend fun getProfile(): ApiResponse<UserResponse>

    @POST("users/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse<Unit>

    @POST("users/send-otp")
    suspend fun sendOtp(@Body request: OtpRequest): ApiResponse<Unit>
}