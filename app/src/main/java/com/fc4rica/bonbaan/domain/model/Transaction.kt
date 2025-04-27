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
    val `object`: String,
    val id: String,
    val livemode: Boolean,
    val location: String?,
    val createdAt: LocalDateTime,
    val status: String,
    val amount: Double,
    val authorizationType: String,
    val authorizedAmount: Double,
    val capturedAmount: Double,
    val currency: String,
    val description: String?,
    val capture: Boolean,
    val authorized: Boolean,
    val reversed: Boolean,
    val paid: Boolean,
    val transaction: String,
    val card: String?,
    val refundedAmount: Double,
    val refunds: String?,
    val failureCode: String?,
    val failureMessage: String?,
    val customer: String,
    val ip: String?,
    val dispute: String?,
    val returnUri: String,
    val authorizeUri: String,
    val sourceOfFund: String,
    val offsite: String,
    val source: String?,
    val metadata: String?,
    val expiresAt: LocalDateTime
)
