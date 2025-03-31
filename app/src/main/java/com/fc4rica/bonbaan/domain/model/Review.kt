package com.fc4rica.bonbaan.domain.model

import java.time.LocalDateTime

data class Review(
    val id: String,
    val user: User?,
    val service: Service?,
    val rating: Int,
    val detail: String,
    val createdAt: LocalDateTime
)
