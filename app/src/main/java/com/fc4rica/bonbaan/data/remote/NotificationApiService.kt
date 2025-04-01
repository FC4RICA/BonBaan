package com.fc4rica.bonbaan.data.remote

import com.fc4rica.bonbaan.data.remote.dto.ApiResponse
import com.fc4rica.bonbaan.data.remote.dto.NotificationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NotificationApiService {
    @GET("users/{id}/notifications")
    suspend fun getNotifications(@Path("id") userId: String): ApiResponse<List<NotificationResponse>>

    @GET("notifications/{id}")
    suspend fun getNotification(@Path("id") notificationId: String): ApiResponse<NotificationResponse>

    @GET("/notifications/{id}/read")
    suspend fun markAsRead(@Path("id") notificationId: String): ApiResponse<NotificationResponse>
}