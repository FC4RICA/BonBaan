package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.Status

interface OrderRepository {
    suspend fun getOrders(): Result<List<Order>>
    suspend fun getOrder(id: String): Result<Order>
    suspend fun getOrdersByStatus(status: String): Result<List<Order>>
    suspend fun createOrder(order: Order): Result<Order>
    suspend fun approveOrder(id: String): Result<Unit>
    suspend fun cancelOrder(id: String): Result<Unit>
    suspend fun completeOrder(id: String): Result<Unit>

    suspend fun getOrderStatuses(): Result<List<Status>>
}