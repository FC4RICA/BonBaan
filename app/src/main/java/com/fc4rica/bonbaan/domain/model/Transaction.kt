package com.fc4rica.bonbaan.domain.model

import java.time.LocalDateTime

data class Transaction(
    val id: String,
    val price: Double,
    val chargeId: String,
    val charge: Charge?,
    val createdAt: LocalDateTime
)

data class Charge(
    val id: String,
    val status: String,
    val amount: Double,
    val currency: String,
    val capturedAmount: Double,
    val authorizedAmount: Double,
    val paid: Boolean,
    val refundedAmount: Double,
    val failureMessage: String?,
    val createdAt: LocalDateTime
)
