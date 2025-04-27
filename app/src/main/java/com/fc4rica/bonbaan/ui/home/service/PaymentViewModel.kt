package com.fc4rica.bonbaan.ui.home.service

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.OrderStatus
import com.fc4rica.bonbaan.domain.model.Status
import com.fc4rica.bonbaan.domain.repository.OrderRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

data class PaymentUiState(
    val order: Order? = null,
    val paymentStatus: Status? = null,
    val isPaid: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class PaymentViewModel(
    private val orderRepository: OrderRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(PaymentUiState())
    val state = _state.asStateFlow()

    init {
        getOrder()
    }

    private fun getOrder() {
        _state.update { it.copy(isLoading = true) }
        val orderId = savedStateHandle.get<String>("orderId") ?: ""
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

    private var pollingJob: Job? = null

    fun startPollingPaymentStatus() {
        pollingJob?.cancel()
        pollingJob = viewModelScope.launch {
            while (isActive) {
                if (_state.value.order != null) {
                    val response = orderRepository.getOrderStatus(_state.value.order!!.id)
                    val status = response.getOrNull() ?: continue

                    _state.update { it.copy(paymentStatus = status) }

                    if (status.name != OrderStatus.Pending.engName) {
                        _state.update { it.copy(isPaid = true) }
                        break
                    }
                }

                delay(10000)
            }
        }
    }

}