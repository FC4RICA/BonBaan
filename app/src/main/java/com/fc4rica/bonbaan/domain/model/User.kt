package com.fc4rica.bonbaan.domain.model

data class User(
    val id: String,
    val email: String,
    val name: String,
    val username: String,
    val phoneNumber: String,
    val token: String
)