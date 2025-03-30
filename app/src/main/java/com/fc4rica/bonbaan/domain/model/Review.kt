package com.fc4rica.bonbaan.domain.model

data class Review(
    val id: String,
    val user: User?,
    val service: Service?,
    val rating: Int,
    val detail: String
)
