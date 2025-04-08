package com.fc4rica.bonbaan.data.repository

import com.fc4rica.bonbaan.data.remote.OrderApiService
import com.fc4rica.bonbaan.data.remote.dto.toOrderType
import com.fc4rica.bonbaan.domain.model.OrderType
import com.fc4rica.bonbaan.domain.repository.OrderTypeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderTypeRepositoryImpl(
    private val orderApiService: OrderApiService
) : OrderTypeRepository {
    private val _orderTypes = MutableStateFlow<List<OrderType>>(emptyList())
    override val orderTypes = _orderTypes.asStateFlow()

    override suspend fun getOrderTypes(): Result<List<OrderType>> {
        if (_orderTypes.value.isNotEmpty()) {
            return Result.success(_orderTypes.value)
        }
        val result = setOrderTypes()
        if (result.isFailure) {
            return Result.failure(result.exceptionOrNull() ?: Exception("Unknown error"))
        }
        return Result.success(_orderTypes.value)
    }

    override suspend fun getOrderTypeById(id: String): OrderType? {
        if (_orderTypes.value.isEmpty()) {
            val result = setOrderTypes()
            if (result.isFailure) return null
        }
        return _orderTypes.value.find { it.id == id }
    }

    private suspend fun setOrderTypes(): Result<Unit> {
        return try {
            val response = orderApiService.getOrderTypes()
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }
            _orderTypes.value = response.data.map { it.toOrderType() }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}