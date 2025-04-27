package com.fc4rica.bonbaan.data.repository

import androidx.datastore.core.DataStore
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.data.remote.OrderApiService
import com.fc4rica.bonbaan.data.remote.dto.ApiResponse
import com.fc4rica.bonbaan.data.remote.dto.OrderResponse
import com.fc4rica.bonbaan.data.remote.dto.toOrder
import com.fc4rica.bonbaan.data.remote.dto.toStatus
import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.Status
import com.fc4rica.bonbaan.domain.model.request.FulfillOrderRequest
import com.fc4rica.bonbaan.domain.model.request.OrderRequest
import com.fc4rica.bonbaan.domain.model.request.VowOrderRequest
import com.fc4rica.bonbaan.domain.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update

class OrderRepositoryImpl(
    private val orderApiService: OrderApiService,
    private val userPreferences: DataStore<UserPreferences>,
) : OrderRepository {
    private val _orderRequest = MutableStateFlow<OrderRequest?>(null)
    override val orderRequest: StateFlow<OrderRequest?> = _orderRequest.asStateFlow()

    override fun setOrderRequest(request: OrderRequest) {
        _orderRequest.value = request
    }

    override fun updateOrderRequest(update: (OrderRequest?) -> OrderRequest?) {
        _orderRequest.update { current -> update(current) }
    }

    override fun clearOrderRequest() {
        _orderRequest.value = null
    }

    override suspend fun sendOrderRequest(): Result<Order> {
        val result = when (val order = _orderRequest.value) {
            is OrderRequest.Vow -> sendVowOrder(order.request)
            is OrderRequest.Fulfill -> sendFulfillOrder(order.request)
            else -> return Result.failure(Exception("Invalid order request"))
        }

        clearOrderRequest()
        return result
    }

    override suspend fun sendVowOrder(request: VowOrderRequest): Result<Order> {
        val response = if (request.packageId.isNotEmpty()) {
            orderApiService.createVowOrder(request)
        } else {
            orderApiService.createCustomVowOrder(request)
        }

        return handleOrderResponse(response)
    }

    override suspend fun sendFulfillOrder(request: FulfillOrderRequest): Result<Order> {
        val response = if (request.packageId.isNotEmpty()) {
            orderApiService.createFulfillOrder(request)
        } else {
            orderApiService.createCustomFulfillOrder(request)
        }

        return handleOrderResponse(response)
    }

    private fun handleOrderResponse(response: ApiResponse<OrderResponse>): Result<Order> {
        response.error?.let { return Result.failure(Exception(it)) }
        response.data ?: return Result.failure(Exception("Empty response"))

        return Result.success(response.data.toOrder())
    }

    override suspend fun getOrders(): Result<List<Order>> {
        return try {
            val userId = userPreferences.data.first().id
                ?: return Result.failure(Exception("User ID not found"))

            val response = orderApiService.getOrders(userId)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            val orders = response.data.map { it.toOrder() }
            Result.success(orders)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getOrder(id: String): Result<Order> {
        return try {
            val response = orderApiService.getOrder(id)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            val orders = response.data.toOrder()
            Result.success(orders)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getOrderStatus(id: String): Result<Status>  {
        return try {
            val response = orderApiService.getOrder(id)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            val orders = response.data.toOrder()
            Result.success(orders.status)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun approveOrder(id: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun cancelOrder(id: String): Result<Unit> {
        return try {
            val response = orderApiService.cancelOrder(id)
            if (response.error != null) {
                return Result.failure(Exception(response.error))
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun completeOrder(id: String): Result<Unit> {
        return try {
            val response = orderApiService.completeOrder(id)
            if (response.error != null) {
                return Result.failure(Exception(response.error))
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    // Order Status
    override suspend fun getOrderStatuses(): Result<List<Status>> {
        return try {
            val response = orderApiService.getOrderStatuses()
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.map { it.toStatus() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getOrdersByStatus(statusId: String): Result<List<Order>> {
        return try {
            val userId = userPreferences.data.first().id
                ?: return Result.failure(Exception("User ID not found"))

            val response = orderApiService.getOrders(userId, statusId)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            val orders = response.data.map { it.toOrder() }
            Result.success(orders)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getOrdersCountByStatus(): Result<Map<Status, Int>> {
        return try {
            val userId = userPreferences.data.first().id
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