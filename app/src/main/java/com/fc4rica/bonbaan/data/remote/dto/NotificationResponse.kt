package com.fc4rica.bonbaan.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("ID")
    val id: String,
    val header: String,
    val body: String,
    @SerializedName("is_read")
    val isRead: Boolean,
    @SerializedName("Order")
    val order: OrderResponse?,
    @SerializedName("CreatedAt")
    val createdAt: String
)
