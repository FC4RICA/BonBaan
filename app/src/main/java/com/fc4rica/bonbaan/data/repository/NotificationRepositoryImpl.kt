package com.fc4rica.bonbaan.data.repository

import androidx.datastore.core.DataStore
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.data.remote.NotificationApiService
import com.fc4rica.bonbaan.data.remote.dto.toNotification
import com.fc4rica.bonbaan.domain.model.Notification
import com.fc4rica.bonbaan.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.first

class NotificationRepositoryImpl(
    private val notificationApiService: NotificationApiService,
    private val userPreferences: DataStore<UserPreferences>,
) : NotificationRepository {
    override suspend fun getNotifications(): Result<List<Notification>> {
        return try {
            val userId = userPreferences.data.first().id
                ?: return Result.failure(Exception("User ID not found"))

            val response = notificationApiService.getUnreadNotifications(userId)

            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.map { it.toNotification() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getNotification(id: String): Result<Notification> {
        return try {
            val response = notificationApiService.getNotification(id)

            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.toNotification())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun markAsRead(id: String): Result<Unit> {
        return try {
            val response = notificationApiService.markAsRead(id)
            if (response.error != null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}