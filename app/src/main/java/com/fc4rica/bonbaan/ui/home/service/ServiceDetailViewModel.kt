package com.fc4rica.bonbaan.ui.home.service

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.OrderType
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.domain.model.PackageType
import com.fc4rica.bonbaan.domain.model.Review
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.model.VowRecord
import com.fc4rica.bonbaan.domain.model.request.FulfillOrderRequest
import com.fc4rica.bonbaan.domain.model.request.OrderRequest
import com.fc4rica.bonbaan.domain.model.request.VowOrderRequest
import com.fc4rica.bonbaan.domain.repository.OrderRepository
import com.fc4rica.bonbaan.domain.repository.OrderTypeRepository
import com.fc4rica.bonbaan.domain.repository.ReviewRepository
import com.fc4rica.bonbaan.domain.repository.ServiceRepository
import com.fc4rica.bonbaan.domain.repository.VowRecordRepository
import com.fc4rica.bonbaan.utils.CategoryUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ServiceDetailUiState(
    val service: Service = Service(
        id = "",
        name = "",
        description = "",
        rate = 0.0,
        address = "",
    ),
    val packages: List<Package> = emptyList(),
    val selectedOrderType: PackageType = PackageType.Vow,
    val selectedPackageId: String = "",
    val reviews: List<Review> = emptyList(),
    val isReviewsLoading: Boolean = false,
    val vowRecord: VowRecord? = null,
    val hasUnFulfilledVowRecords: Boolean = false,
    val isOrdering: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ServiceDetailViewModel(
    private val serviceRepository: ServiceRepository,
    private val reviewRepository: ReviewRepository,
    private val vowRecordRepository: VowRecordRepository,
    private val orderRepository: OrderRepository,
    private val orderTypeRepository: OrderTypeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(ServiceDetailUiState())
    val state = _state.asStateFlow()

    private val _serviceId = savedStateHandle.get<String>("serviceId") ?: ""

    init {
        getUnFulfilledVowRecords()
        getServiceDetail()
        getReviews()
    }

    fun updateSelectedOrderType(type: PackageType) {
        _state.update {
            it.copy(
                selectedOrderType = type,
                selectedPackageId = it.packages.first { it.orderType.name == type.displayName }.id
            )
        }
    }

    fun updateSelectedPackageId(id: String) {
        _state.update { it.copy(selectedPackageId = id) }
    }

    fun resetOrdering() {
        _state.update { it.copy(isOrdering = false) }
    }

    fun createOrder() {
        viewModelScope.launch {
            when (state.value.selectedOrderType) {
                PackageType.Vow -> {
                    if (createVowOrderRequest())
                        _state.update { it.copy(isOrdering = true) }
                }

                PackageType.Fulfill -> {
                    if (createFulfillOrderRequest())
                        _state.update { it.copy(isOrdering = true) }
                }
            }
        }
    }

    private suspend fun createVowOrderRequest(): Boolean {
        val orderTypeResult = orderTypeRepository.getOrderTypes()
        val orderTypeId =
            orderTypeResult.getOrDefault(emptyList())
                .find { it.name == PackageType.Vow.displayName }?.id
        if (orderTypeId.isNullOrEmpty()) {
            _state.update { it.copy(errorMessage = "Network problem") }
            return false
        }
        val request = OrderRequest.Vow(
            VowOrderRequest(
                deadline = "",
                note = "",
                vow = "",
                price = 0.0,
                items = emptyList(),
                packageId = _state.value.selectedPackageId,
                serviceId = _serviceId,
                orderTypeID = orderTypeId
            )
        )
        orderRepository.setOrderRequest(request)
        return true
    }

    private suspend fun createFulfillOrderRequest(): Boolean {
        val orderTypeResult = orderTypeRepository.getOrderTypes()
        val orderTypeId =
            orderTypeResult.getOrDefault(emptyList())
                .find { it.name == PackageType.Fulfill.displayName }?.id
        if (orderTypeId.isNullOrEmpty()) {
            _state.update { it.copy(errorMessage = "Network problem") }
            return false
        }
        if (_state.value.vowRecord == null) {
            _state.update { it.copy(errorMessage = "กรุณาเพิ่มรายการบนบานก่อน") }
            return false
        }
        val request = OrderRequest.Fulfill(
            FulfillOrderRequest(
                price = 0.0,
                items = emptyList(),
                packageId = _state.value.selectedPackageId,
                serviceId = _serviceId,
                vowRecordID = _state.value.vowRecord!!.id,
                orderTypeID = orderTypeId
            )
        )
        orderRepository.setOrderRequest(request)
        return true
    }

    private fun getServiceDetail() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = serviceRepository.getService(_serviceId)
            result.fold(
                onSuccess = { service ->
                    val mappedService =
                        service.copy(categories = CategoryUtils.mapCategoriesIcon(service.categories))
                    _state.update {
                        it.copy(
                            service = mappedService,
                            packages = mappedService.packages,
                            isLoading = false
                        )
                    }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }

        // insert custom package
        PackageType.entries.map { packageType ->
            val customPackage = Package(
                id = "",
                name = "แพ็กเกจ${packageType.displayName}แบบกำหนดเอง",
                description = "คุณสามารถกำหนดรายการสินค้าที่ต้องการให้เราจัดหาให้ได้เอง จากนั้นเราจึงจะส่งค่าใช้จ่ายให้คุณภายหลัง",
                price = 0.0,
                orderType = OrderType(id = "", name = packageType.displayName),
                items = emptyList(),
            )
            _state.update {
                it.copy(
                    packages = it.packages + customPackage
                )
            }
            _state.update {
                it.copy(
                    selectedPackageId = it.packages.first().id
                )
            }
        }
    }

    private fun getReviews() {
        _state.update { it.copy(isReviewsLoading = true) }
        viewModelScope.launch {
            val result = reviewRepository.getReviewsByService(_serviceId)
            result.fold(
                onSuccess = { reviews ->
                    _state.update { it.copy(reviews = reviews, isReviewsLoading = false) }
                },
                onFailure = { error ->
                    _state.update {
                        it.copy(
                            errorMessage = error.message,
                            isReviewsLoading = false
                        )
                    }
                }
            )
        }
    }

    private fun getUnFulfilledVowRecords() {
        viewModelScope.launch {
            val result = vowRecordRepository.getUnFulfilledVowRecordsByService(_serviceId)
            result.fold(
                onSuccess = { vowRecords ->
                    _state.update {
                        it.copy(
                            hasUnFulfilledVowRecords = true,
                            vowRecord = vowRecords.first()
                        )
                    }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message) }
                }
            )

        }
    }

}