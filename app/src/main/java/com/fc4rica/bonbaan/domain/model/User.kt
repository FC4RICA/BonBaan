package com.fc4rica.bonbaan.domain.model

data class User(
    val id: String,
    val email: String,
    val firstname: String,
    val lastname: String,
    val username: String,
    val phoneNumber: String,
    val token: String
)