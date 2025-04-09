package com.fc4rica.bonbaan.ui.home.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.domain.model.request.FulfillOrderRequest
import com.fc4rica.bonbaan.domain.model.request.OrderRequest
import com.fc4rica.bonbaan.domain.model.request.VowOrderRequest
import com.fc4rica.bonbaan.domain.repository.OrderRepository
import com.fc4rica.bonbaan.domain.repository.PackageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OrderUiState(
    val packages: List<Package> = emptyList(),
    val selectedPackageId: String? = null,
    val isVow: Boolean = true,
    val isFulfill: Boolean = false,
    val orderRequest: OrderRequest? = null,
    val errorMessage: String? = null
)

class OrderViewModel(
    private val packageRepository: PackageRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {
    private val _state = MutableStateFlow(OrderUiState())
    val state = _state.asStateFlow()

    init {
        val orderRequest = getOrderRequest()
        getPackages(orderRequest)
    }

    private fun getOrderRequest(): OrderRequest? {
        val orderRequest = orderRepository.orderRequest.value
        _state.update {
            it.copy(
                orderRequest = orderRequest,
                isVow = orderRequest is OrderRequest.Vow,
                isFulfill = orderRequest is OrderRequest.Fulfill
            )
        }
        return orderRequest
    }

    private fun getPackages(orderRequest: OrderRequest?) {
        val (serviceId, orderTypeId) = when (orderRequest) {
            is OrderRequest.Vow -> orderRequest.request.serviceId to orderRequest.request.orderTypeID
            is OrderRequest.Fulfill -> orderRequest.request.serviceId to orderRequest.request.orderTypeID
            else -> null
        } ?: return
        viewModelScope.launch {
            val result = packageRepository.getPackagesByService(serviceId!!)
            result.fold(
                onSuccess = { packages ->
                    _state.update { it.copy(packages = packages.filter { pack -> pack.orderType.id == orderTypeId }) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message) }
                }
            )
        }
    }

    fun updateSelectedPackageId(id: String) {
        _state.update { it.copy(selectedPackageId = id) }
    }

    fun updateVow(vow: String) {
        updateVowField { copy(vow = vow) }
    }

    fun updateNote(note: String) {
        updateVowField { copy(note = note) }
    }

    fun updateDeadline(deadline: String) {
        updateVowField { copy(deadline = deadline) }
    }

    fun updateCustomItem(items: String) {
        val lines = items.split("\n")
        when (_state.value.orderRequest) {
            is OrderRequest.Vow -> updateVowField { copy(items = lines) }
            is OrderRequest.Fulfill -> updateFulfillField { copy(items = lines) }
            else -> Unit
        }
    }

    private fun updateVowField(update: VowOrderRequest.() -> VowOrderRequest) {
        val current = (_state.value.orderRequest as? OrderRequest.Vow)?.request ?: return
        val wrapped = OrderRequest.Vow(update(current))
        _state.update { it.copy(orderRequest = wrapped) }
    }

    private fun updateFulfillField(update: FulfillOrderRequest.() -> FulfillOrderRequest) {
        val current = (_state.value.orderRequest as? OrderRequest.Fulfill)?.request ?: return
        val wrapped = OrderRequest.Fulfill(update(current))
        _state.update { it.copy(orderRequest = wrapped) }
    }

    fun submitVowOrder() {
        val current = (_state.value.orderRequest as? OrderRequest.Vow)?.request ?: return
        val wrapped = OrderRequest.Vow(current)
        orderRepository.setOrderRequest(wrapped)
    }

    fun submitFulfillOrder() {
        val current = (_state.value.orderRequest as? OrderRequest.Fulfill)?.request ?: return
        val wrapped = OrderRequest.Fulfill(current)
        orderRepository.setOrderRequest(wrapped)
    }
}