package com.fc4rica.bonbaan.domain.model

data class Package(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val items: List<String> = emptyList(),
    val orderType: OrderType,
    val service: Service? = null
)
