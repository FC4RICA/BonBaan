package com.fc4rica.bonbaan.domain.model

import java.time.LocalDateTime

data class Notification(
    val id: String,
    val header: String,
    val body: String,
    val isRead: Boolean,
    val createdAt: LocalDateTime,
    val order: Order?,
)
