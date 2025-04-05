package com.fc4rica.bonbaan.ui.home.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OrderStatusDetailUiState(
    val order: Order? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

class OrderStatusDetailViewModel(
    private val orderRepository: OrderRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(OrderStatusDetailUiState())
    val state = _state.asStateFlow()

    private val orderId: String = checkNotNull(savedStateHandle["orderId"])

    init {
        getOrderDetail()
    }

    private fun getOrderDetail() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = orderRepository.getOrder(orderId)

            result.fold(
                onSuccess = { order ->
                    _state.update { it.copy(order = order, isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }
    }
}