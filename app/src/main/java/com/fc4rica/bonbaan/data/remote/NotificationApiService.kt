package com.fc4rica.bonbaan.data.remote

import com.fc4rica.bonbaan.data.remote.dto.ApiResponse
import com.fc4rica.bonbaan.data.remote.dto.NotificationResponse
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface NotificationApiService {
    @GET("users/{id}/notifications")
    suspend fun getUnreadNotifications(
        @Path("id") userId: String,
        @Query("is-read") isRead: Boolean = false
    ): ApiResponse<List<NotificationResponse>>

    @GET("notifications/{id}")
    suspend fun getNotification(@Path("id") notificationId: String): ApiResponse<NotificationResponse>

    @PATCH("notifications/{id}/read")
    suspend fun markAsRead(@Path("id") notificationId: String): ApiResponse<Unit>
}