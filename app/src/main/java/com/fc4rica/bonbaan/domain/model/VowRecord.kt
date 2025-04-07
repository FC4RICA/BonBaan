package com.fc4rica.bonbaan.domain.model

import java.time.LocalDateTime

data class VowRecord(
    val id: String,
    val vow: String,
    val deadline: LocalDateTime,
    val note: String?,
    val user: User?,
    val service: Service?,
    val vowOrder: Order?,
    val fulfillOrder: Order?,
    val createdAt: LocalDateTime
)
