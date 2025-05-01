package com.fc4rica.bonbaan.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.OrderStatus
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.repository.AuthRepository
import com.fc4rica.bonbaan.domain.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileUiState(
    val user: User? = null,
    val statusDisplayItems: List<StatusDisplayItem> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

enum class OrderStatusUI(val orderStatus: OrderStatus) {
    Pending(OrderStatus.Pending),
    Unpaid(OrderStatus.Unpaid),
    Processing(OrderStatus.Processing),
    Confirm(OrderStatus.Confirm),
    Review(OrderStatus.Review)
}

data class StatusDisplayItem(
    val orderStatus: OrderStatus,
    val id: String,
    val count: Int
)

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    private val _isProfileLoading = MutableStateFlow(false)
    private val _isOrdersCountLoading = MutableStateFlow(false)

    init {
        getProfile()
        getOrdersCount()

        viewModelScope.launch {
            combine(_isProfileLoading, _isOrdersCountLoading) { profileLoading, ordersLoading ->
                profileLoading || ordersLoading
            }.collect { isLoading ->
                _state.update { it.copy(isLoading = isLoading) }
            }
        }
    }

    private fun getProfile() {
        _isProfileLoading.value = true
        viewModelScope.launch {
            val result = authRepository.getProfile()
            result.fold(
                onSuccess = { user ->
                    _state.update { it.copy(user = user, isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
            _isProfileLoading.value = false
        }
    }

    private fun getOrdersCount() {
        _isOrdersCountLoading.value = true
        viewModelScope.launch {
            val result = orderRepository.getOrdersCountByStatus()
            result.fold(
                onSuccess = { ordersCountByStatus ->
                    val statusDisplayItems = OrderStatusUI.entries.map { orderStatusUI ->
                        val matched = ordersCountByStatus.entries
                            .firstOrNull {
                                it.key.name.equals(
                                    orderStatusUI.orderStatus.engName,
                                    true
                                )
                            }

                        StatusDisplayItem(
                            orderStatus = orderStatusUI.orderStatus,
                            id = matched?.key?.id ?: "",
                            count = matched?.value ?: 0
                        )
                    }
                    _state.update { it.copy(statusDisplayItems = statusDisplayItems) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message) }
                }
            )
            _isOrdersCountLoading.value = false
        }
    }
}