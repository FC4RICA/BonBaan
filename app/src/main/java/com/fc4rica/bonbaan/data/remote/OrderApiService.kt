package com.fc4rica.bonbaan.data.remote

import com.fc4rica.bonbaan.data.remote.dto.ApiResponse
import com.fc4rica.bonbaan.data.remote.dto.OrderResponse
import com.fc4rica.bonbaan.data.remote.dto.OrderTypeResponse
import com.fc4rica.bonbaan.data.remote.dto.OrdersResponse
import com.fc4rica.bonbaan.data.remote.dto.StatusResponse
import com.fc4rica.bonbaan.domain.model.request.FulfillOrderRequest
import com.fc4rica.bonbaan.domain.model.request.VowOrderRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderApiService {
    @GET("users/{id}/orders")
    suspend fun getOrders(@Path("id") userId: String, @Query("status") statusID: String? = null): ApiResponse<OrdersResponse>

    @GET("orders/{id}")
    suspend fun getOrder(@Path("id") orderId: String): ApiResponse<OrderResponse>

    @POST("orders")
    suspend fun createVowOrder(@Body order: VowOrderRequest): ApiResponse<OrderResponse>

    @POST("orders")
    suspend fun createFulfillOrder(@Body order: FulfillOrderRequest): ApiResponse<OrderResponse>

    @POST("orders/custom-order")
    suspend fun createCustomVowOrder(@Body order: VowOrderRequest): ApiResponse<OrderResponse>

    @POST("orders/custom-order")
    suspend fun createCustomFulfillOrder(@Body order: FulfillOrderRequest): ApiResponse<OrderResponse>

    @POST("orders/{id}/approve")
    suspend fun approveOrder(@Path("id") orderId: String): ApiResponse<Unit>

    @POST("orders/{id}/cancel")
    suspend fun cancelOrder(@Path("id") orderId: String): ApiResponse<Unit>

    @POST("orders/{id}/complete")
    suspend fun completeOrder(@Path("id") orderId: String): ApiResponse<Unit>

    // Status
    @GET("statuses")
    suspend fun getOrderStatuses(): ApiResponse<List<StatusResponse>>

    // Order Type
    @GET("order-types")
    suspend fun getOrderTypes(): ApiResponse<List<OrderTypeResponse>>
}