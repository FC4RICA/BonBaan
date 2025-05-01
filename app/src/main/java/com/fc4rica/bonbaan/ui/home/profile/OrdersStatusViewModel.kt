package com.fc4rica.bonbaan.ui.home.profile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.Status
import com.fc4rica.bonbaan.domain.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OrdersStatusUiState(
    val orders: List<Order> = emptyList(),
    val status: List<Status> = emptyList(),
    val selectedStatus: Status? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

class OrdersStatusViewModel(
    private val orderRepository: OrderRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    private val _state = MutableStateFlow(OrdersStatusUiState())
    val state = _state.asStateFlow()

    private val _isOrderLoading = MutableStateFlow(false)
    private val _isStatusLoading = MutableStateFlow(false)

    private val _statusId = savedStateHandle.get<String>("statusId") ?: ""

    init {
        getOrders()
        getStatuses()
        filterOrdersByStatus(_statusId)

        viewModelScope.launch {
            combine(_isOrderLoading, _isStatusLoading) { profileLoading, serviceLoading ->
                profileLoading || serviceLoading
            }.collect { isLoading ->
                _state.update { it.copy(isLoading = isLoading) }
            }
        }
    }

    private fun getOrders() {
        _isOrderLoading.value = true
        viewModelScope.launch {
            val result = orderRepository.getOrders()
            Log.d("OrdersStatusViewModel", "getOrders: $result")
            result.fold(
                onSuccess = { orders ->
                    _orders.value = orders.sortedByDescending { it.createdAt }
                    _state.update { it.copy(orders = _orders.value, isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
            _isOrderLoading.value = false
        }
    }

    private fun getStatuses() {
        _isStatusLoading.value = true
        viewModelScope.launch {
            val result = orderRepository.getOrderStatuses()
            Log.d("OrdersStatusViewModel", "getStatuses: $result")
            result.fold(
                onSuccess = { statuses ->
                    _state.update { it.copy(
                        status = listOf(Status(id = "", name = "ทั้งหมด")) + statuses,
                        selectedStatus = statuses.find { status -> status.id == _statusId },
                        isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
            _isStatusLoading.value = false
        }
    }

    fun filterOrdersByStatus(statusId: String?) {
        val selectedStatus = _state.value.status.find { it.id == statusId }
        _state.update { it.copy(isLoading = true, selectedStatus = selectedStatus) }
        val filteredList = if (statusId.isNullOrEmpty()) {
            _orders.value
        } else {
            _orders.value.filter { it.status.id == statusId }
        }
        Log.d("OrdersStatusViewModel", "filterOrdersByStatus: $filteredList")
        _state.update { it.copy(orders = filteredList, isLoading = false) }
    }
}