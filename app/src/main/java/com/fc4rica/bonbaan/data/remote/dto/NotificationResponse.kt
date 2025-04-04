package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.Notification
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

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

fun NotificationResponse.toNotification(): Notification {
    return Notification(
        id = id,
        header = header,
        body = body,
        isRead = isRead,
        createdAt = LocalDateTime.parse(createdAt),
        orderId = order?.id ?: ""
    )
}