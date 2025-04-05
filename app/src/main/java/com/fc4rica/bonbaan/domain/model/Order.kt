package com.fc4rica.bonbaan.domain.model

import java.time.LocalDateTime

data class Order(
    val id: String,
    val price: Double,
    val items: List<String> = emptyList(),
    val packageItem: Package?,
    val createdAt: LocalDateTime,
    val transaction: Transaction?,
    val cancellationReason: String?,
    val status: Status,
    val attachments: List<Attachment> = emptyList(),
    val service: Service?
)
