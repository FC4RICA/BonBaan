package com.fc4rica.bonbaan.domain.model.request

data class RegisterRequest(
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val password: String,
    val code: String
)
