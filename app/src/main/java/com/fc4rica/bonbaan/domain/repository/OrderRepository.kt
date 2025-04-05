package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.Status
import com.fc4rica.bonbaan.domain.model.request.FulfillOrderRequest
import com.fc4rica.bonbaan.domain.model.request.VowOrderRequest

interface OrderRepository {
    suspend fun getOrders(): Result<List<Order>>
    suspend fun getOrder(id: String): Result<Order>

    suspend fun createVowOrder(request: VowOrderRequest): Result<Order>
    suspend fun createFulfillOrder(request: FulfillOrderRequest): Result<Order>
    suspend fun approveOrder(id: String): Result<Unit>
    suspend fun cancelOrder(id: String): Result<Unit>
    suspend fun completeOrder(id: String): Result<Unit>

    suspend fun getOrderStatuses(): Result<List<Status>>
    suspend fun getOrdersByStatus(statusId: String): Result<List<Order>>
    suspend fun getOrdersCountByStatus(): Result<Map<Status, Int>>
}