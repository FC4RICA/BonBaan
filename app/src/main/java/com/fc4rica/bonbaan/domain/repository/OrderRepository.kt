package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.Status
import com.fc4rica.bonbaan.domain.model.request.FulfillOrderRequest
import com.fc4rica.bonbaan.domain.model.request.OrderRequest
import com.fc4rica.bonbaan.domain.model.request.VowOrderRequest
import kotlinx.coroutines.flow.StateFlow

interface OrderRepository {
    val orderRequest: StateFlow<OrderRequest?>
    fun setOrderRequest(request: OrderRequest)
    fun updateOrderRequest(update: (OrderRequest?) -> OrderRequest?)
    fun clearOrderRequest()
    suspend fun sendOrderRequest(): Result<Order>
    suspend fun sendVowOrder(request: VowOrderRequest): Result<Order>
    suspend fun sendFulfillOrder(request: FulfillOrderRequest): Result<Order>

    suspend fun getOrders(): Result<List<Order>>
    suspend fun getOrder(id: String): Result<Order>
    suspend fun getOrderStatus(id: String): Result<Status>

    suspend fun approveOrder(id: String): Result<Unit>
    suspend fun cancelOrder(id: String): Result<Unit>
    suspend fun completeOrder(id: String): Result<Unit>

    suspend fun getOrderStatuses(): Result<List<Status>>
    suspend fun getOrdersByStatus(statusId: String): Result<List<Order>>
    suspend fun getOrdersCountByStatus(): Result<Map<Status, Int>>
}