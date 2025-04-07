package com.fc4rica.bonbaan.data.remote.dto

data class ApiResponse<T>(
    val data: T?,
    val error: String?,
    val message: String?
)
