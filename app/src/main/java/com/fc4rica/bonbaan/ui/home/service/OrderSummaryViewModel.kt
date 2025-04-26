package com.fc4rica.bonbaan.ui.home.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.model.VowRecord
import com.fc4rica.bonbaan.domain.model.request.FulfillOrderRequest
import com.fc4rica.bonbaan.domain.model.request.OrderRequest
import com.fc4rica.bonbaan.domain.model.request.VowOrderRequest
import com.fc4rica.bonbaan.domain.repository.AuthRepository
import com.fc4rica.bonbaan.domain.repository.OrderRepository
import com.fc4rica.bonbaan.domain.repository.ServiceRepository
import com.fc4rica.bonbaan.domain.repository.VowRecordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OrderSummaryUiState(
    val vowOrderRequest: VowOrderRequest? = null,
    val fulfillOrderRequest: FulfillOrderRequest? = null,
    val service: Service? = null,
    val packageItem: Package? = null,
    val user: User? = null,
    val vowRecord: VowRecord? = null,
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val errorMessage: String? = null
)

class OrderSummaryViewModel(
    private val orderRepository: OrderRepository,
    private val serviceRepository: ServiceRepository,
    private val authRepository: AuthRepository,
    private val vowRecordRepository: VowRecordRepository
) : ViewModel() {
    private val _state = MutableStateFlow(OrderSummaryUiState())
    val state = _state.asStateFlow()

    init {
        val serviceId = getOrderRequest()
        getServiceAndPackage(serviceId)
        getUser()
    }

    fun confirmSubmitOrder() {
        _state.update { it.copy(isLoading = true) }
        if (_state.value.vowOrderRequest != null) {
            val wrapped = OrderRequest.Vow(_state.value.vowOrderRequest!!)
            orderRepository.setOrderRequest(wrapped)
        } else if (_state.value.fulfillOrderRequest != null) {
            val wrapped = OrderRequest.Fulfill(_state.value.fulfillOrderRequest!!)
            orderRepository.setOrderRequest(wrapped)
        }
        viewModelScope.launch {
            val result = orderRepository.sendOrderRequest()
            result.fold(
                onSuccess = { _state.update { it.copy(isSuccessful = true, isLoading = false) } },
                onFailure = { error ->
                    _state.update {
                        it.copy(
                            errorMessage = error.message,
                            isLoading = false
                        )
                    }
                }
            )
        }
    }

    private fun getOrderRequest(): String? {
        _state.update { it.copy(isLoading = true) }
        val orderRequest = orderRepository.orderRequest.value
        _state.value = when (orderRequest) {
            is OrderRequest.Vow -> OrderSummaryUiState(vowOrderRequest = orderRequest.request)
            is OrderRequest.Fulfill -> OrderSummaryUiState(fulfillOrderRequest = orderRequest.request)
            else -> OrderSummaryUiState(errorMessage = "Invalid order request", isLoading = false)
        }

        if (_state.value.fulfillOrderRequest != null) {
            viewModelScope.launch {
                val result =
                    vowRecordRepository.getVowRecord(_state.value.fulfillOrderRequest?.vowRecordID!!)
                result.fold(
                    onSuccess = { vowRecord ->
                        _state.update { it.copy(vowRecord = vowRecord) }
                    },
                    onFailure = { error ->
                        _state.update {
                            it.copy(
                                errorMessage = error.message,
                                isLoading = false
                            )
                        }
                    }
                )
            }
        }

        return _state.value.vowOrderRequest?.serviceId
            ?: _state.value.fulfillOrderRequest?.serviceId
    }

    private fun getServiceAndPackage(id: String?) {
        if (id == null) return _state.update {
            it.copy(
                errorMessage = "Invalid order request",
                isLoading = false
            )
        }
        viewModelScope.launch {
            val result = serviceRepository.getService(id)
            result.fold(
                onSuccess = { service ->
                    _state.update {
                        it.copy(
                            service = service,
                            packageItem = service.packages.find { pack ->
                                pack.id == (_state.value.vowOrderRequest?.packageId
                                    ?: _state.value.fulfillOrderRequest?.packageId)
                            }
                        )
                    }
                },
                onFailure = { error ->
                    _state.update {
                        it.copy(
                            errorMessage = error.message,
                            isLoading = false
                        )
                    }
                }
            )
        }
        _state.update { it.copy(isLoading = false) }
    }

    private fun getUser() {
        viewModelScope.launch {
            val result = authRepository.getProfile()
            result.onSuccess {
                _state.update { it.copy(user = it.user) }
            }
        }
    }
}