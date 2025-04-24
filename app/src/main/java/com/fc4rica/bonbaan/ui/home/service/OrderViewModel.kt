package com.fc4rica.bonbaan.ui.home.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.OrderType
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.domain.model.PackageType
import com.fc4rica.bonbaan.domain.model.VowRecord
import com.fc4rica.bonbaan.domain.model.request.FulfillOrderRequest
import com.fc4rica.bonbaan.domain.model.request.OrderRequest
import com.fc4rica.bonbaan.domain.model.request.VowOrderRequest
import com.fc4rica.bonbaan.domain.repository.OrderRepository
import com.fc4rica.bonbaan.domain.repository.PackageRepository
import com.fc4rica.bonbaan.domain.repository.VowRecordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OrderUiState(
    val packages: List<Package> = emptyList(),
    val selectedPackage: Package? = null,
    val isVow: Boolean = true,
    val isFulfill: Boolean = false,
    val vow: String = "",
    val note: String = "",
    val deadline: String = "",
    val customItem: String = "",
    val fulfilledVowRecord: VowRecord? = null,
    val vowRecords: List<VowRecord> = emptyList(),
    val errorMessage: String? = null
)

private data class PackageContext(
    val serviceId: String,
    val orderTypeId: String,
    val orderTypeName: String,
    val selectedPackageId: String,
)

class OrderViewModel(
    private val packageRepository: PackageRepository,
    private val orderRepository: OrderRepository,
    private val vowRecordRepository: VowRecordRepository
) : ViewModel() {
    private val _state = MutableStateFlow(OrderUiState())
    val state = _state.asStateFlow()

    private val _orderRequest = MutableStateFlow<OrderRequest?>(null)

    init {
        val orderRequest = getOrderRequest()
        getPackages(orderRequest)
    }

    private fun getOrderRequest(): OrderRequest? {
        val orderRequest = orderRepository.orderRequest.value
        _state.update {
            it.copy(
                isVow = orderRequest is OrderRequest.Vow,
                isFulfill = orderRequest is OrderRequest.Fulfill
            )
        }

        if (orderRequest is OrderRequest.Fulfill) {
            viewModelScope.launch {
                val result =
                    vowRecordRepository.getUnFulfilledVowRecordsByService(orderRequest.request.serviceId)
                result.fold(
                    onSuccess = { records ->
                        _state.update {
                            it.copy(
                                fulfilledVowRecord = records.find { pack -> pack.id == orderRequest.request.vowRecordID },
                                vowRecords = records
                            )
                        }
                    },
                    onFailure = { error ->
                        _state.update { it.copy(errorMessage = error.message) }
                    }
                )
            }
        }

        _orderRequest.update { orderRequest }

        return orderRequest
    }

    private fun getPackages(orderRequest: OrderRequest?) {
        val packageContext = when (orderRequest) {
            is OrderRequest.Vow -> PackageContext(
                orderRequest.request.serviceId,
                orderRequest.request.orderTypeID,
                PackageType.Vow.name,
                orderRequest.request.packageId
            )

            is OrderRequest.Fulfill -> PackageContext(
                orderRequest.request.serviceId,
                orderRequest.request.orderTypeID,
                PackageType.Fulfill.name,
                orderRequest.request.packageId
            )

            else -> null
        } ?: return

        viewModelScope.launch {
            val result = packageRepository.getPackagesByService(packageContext.serviceId)
            result.fold(
                onSuccess = { packages ->
                    _state.update { it.copy(packages = packages.filter { pack -> pack.orderType.id == packageContext.orderTypeId }) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message) }
                }
            )
        }

        val customPackage = Package(
            id = "",
            name = "แพ็กเกจ${packageContext.orderTypeName}แบบกำหนดเอง",
            description = "คุณสามารถกำหนดรายการสินค้าที่ต้องการให้เราจัดหาให้ได้เอง จากนั้นเราจึงจะส่งค่าใช้จ่ายให้คุณภายหลัง",
            price = 0.0,
            items = listOf(""),
            orderType = OrderType(
                id = packageContext.orderTypeId,
                name = packageContext.orderTypeName
            ),
        )

        _state.update {
            it.copy(
                packages = it.packages + customPackage,
                selectedPackage = it.packages.find { pack -> pack.id == packageContext.selectedPackageId })
        }
    }

    fun updateSelectedPackage(packageItem: Package) {
        _state.update { it.copy(selectedPackage = packageItem) }
        updateVowField { copy(packageId = packageItem.id) }
    }

    fun updateVow(vow: String) {
        _state.update { it.copy(vow = vow) }
        updateVowField { copy(vow = vow) }
    }

    fun updateNote(note: String) {
        _state.update { it.copy(note = note) }
        updateVowField { copy(note = note) }
    }

    fun updateDeadline(deadline: String) {
        _state.update { it.copy(deadline = deadline) }
        updateVowField { copy(deadline = deadline) }
    }

    fun updateCustomItem(items: String) {
        val lines = items.split("\n")
        when (_orderRequest.value) {
            is OrderRequest.Vow -> updateVowField { copy(items = lines) }
            is OrderRequest.Fulfill -> updateFulfillField { copy(items = lines) }
            else -> Unit
        }
    }

    private fun updateVowField(update: VowOrderRequest.() -> VowOrderRequest) {
        val current = (_orderRequest.value as? OrderRequest.Vow)?.request ?: return
        val wrapped = OrderRequest.Vow(update(current))
        _orderRequest.update { wrapped }
    }

    private fun updateFulfillField(update: FulfillOrderRequest.() -> FulfillOrderRequest) {
        val current = (_orderRequest.value as? OrderRequest.Fulfill)?.request ?: return
        val wrapped = OrderRequest.Fulfill(update(current))
        _orderRequest.update { wrapped }
    }

    fun submitVowOrder() {
        val current = (_orderRequest.value as? OrderRequest.Vow)?.request ?: return
        val wrapped = OrderRequest.Vow(current)
        orderRepository.setOrderRequest(wrapped)
    }

    fun submitFulfillOrder() {
        val current = (_orderRequest.value as? OrderRequest.Fulfill)?.request ?: return
        val wrapped = OrderRequest.Fulfill(current)
        orderRepository.setOrderRequest(wrapped)
    }
}