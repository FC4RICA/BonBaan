package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.OrderType
import kotlinx.coroutines.flow.StateFlow

interface OrderTypeRepository {
    val orderTypes: StateFlow<List<OrderType>>

    suspend fun getOrderTypes(): Result<List<OrderType>>
    suspend fun getOrderTypeById(id: String): OrderType?
}