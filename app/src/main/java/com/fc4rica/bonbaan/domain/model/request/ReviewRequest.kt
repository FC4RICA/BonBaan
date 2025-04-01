package com.fc4rica.bonbaan.domain.model.request

data class ReviewRequest(
    val serviceId: String,
    val rating: Int,
    val detail: String,
)
