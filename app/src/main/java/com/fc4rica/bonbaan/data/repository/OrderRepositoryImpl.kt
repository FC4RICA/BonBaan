package com.fc4rica.bonbaan.data.repository

import com.fc4rica.bonbaan.data.local.SecurePreferences
import com.fc4rica.bonbaan.data.remote.OrderApiService
import com.fc4rica.bonbaan.data.remote.dto.toOrder
import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.Status
import com.fc4rica.bonbaan.domain.repository.OrderRepository

class OrderRepositoryImpl(
    private val orderApiService: OrderApiService,
    private val securePreferences: SecurePreferences
) : OrderRepository {
    override suspend fun getOrders(): Result<List<Order>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOrder(id: String): Result<Order> {
        TODO("Not yet implemented")
    }


    override suspend fun createOrder(order: Order): Result<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun approveOrder(id: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun cancelOrder(id: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun completeOrder(id: String): Result<Unit> {
        TODO("Not yet implemented")
    }


    // Order Status
    override suspend fun getOrderStatuses(): Result<List<Status>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOrdersByStatus(statusId: String): Result<List<Order>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOrdersCountByStatus(): Result<Map<Status, Int>> {
        return try {
            val userId = securePreferences.getUserData()?.id
                ?: return Result.failure(Exception("User ID not found"))

            val response = orderApiService.getOrders(userId)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            val orders = response.data.map {
                it.toOrder(
                    mapPackage = false,
                    mapTransaction = false,
                    mapService = false,
                    mapAttachments = false
                )
            }
            val ordersCountByStatus = orders.groupBy { it.status }.mapValues { it.value.size }
            Result.success(ordersCountByStatus)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}