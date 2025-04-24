package com.fc4rica.bonbaan.domain.model

data class OrderType(
    val id: String,
    val name: String
)

enum class PackageType(val displayName: String) { Vow("บนบาน"), Fulfill("แก้บน") }