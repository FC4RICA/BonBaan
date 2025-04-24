package com.fc4rica.bonbaan.domain.model

import java.time.LocalDateTime

data class Review(
    val id: String,
    val user: User? = null,
    val service: Service? = null,
    val rating: Double,
    val detail: String,
    val createdAt: LocalDateTime
)
