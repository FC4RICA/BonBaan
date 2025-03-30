package com.fc4rica.bonbaan.domain.model

data class Transaction(
    val id: String,
    val price: Double,
    val chargeId: String,
    val charge: String
)
