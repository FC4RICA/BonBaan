package com.fc4rica.bonbaan.domain.model

data class Service(
    val id: String,
    val name: String,
    val description: String,
    val rate: Double,
    val address: String,
    val categories: List<Category> = emptyList(),
    val packages: List<Package> = emptyList(),
    val attachments: List<Attachment> = emptyList(),
    val reviews: List<Review> = emptyList()
)
