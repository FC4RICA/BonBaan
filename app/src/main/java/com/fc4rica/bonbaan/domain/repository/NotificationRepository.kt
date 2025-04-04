package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.Notification

interface NotificationRepository {
    suspend fun getNotifications(): Result<List<Notification>>
    suspend fun getNotification(id: String): Result<Notification>
    suspend fun markAsRead(id: String): Result<Unit>
}