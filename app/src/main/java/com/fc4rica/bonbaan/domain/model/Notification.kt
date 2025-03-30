package com.fc4rica.bonbaan.domain.model

data class Notification(
    val id: String,
    val header: String,
    val body: String,
    val isRead: Boolean,
    val order: Order?,
)
