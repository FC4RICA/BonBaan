package com.fc4rica.bonbaan.domain.model

import java.time.LocalDateTime

data class VowRecord(
    val id: String,
    val vow: String,
    val deadline: LocalDateTime,
    val note: String?,
    val service: Service? = null,
    val vowOrder: Order? = null,
    val fulfillOrder: Order? = null,
    val createdAt: LocalDateTime
)
