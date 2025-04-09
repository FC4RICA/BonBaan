package com.fc4rica.bonbaan.ui.home.service

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

// TODO add category mapping

data class ServiceDetailUiState(
    val service: Service? = null,
    val selectedPackageId: String? = null,
    val reviews: List<Review> = emptyList(),
    val isReviewsLoading: Boolean = false,
    val vowRecord: VowRecord? = null,
    val hasUnFulfilledVowRecords: Boolean = false,
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

    fun createVowOrderRequest() {
        viewModelScope.launch {
            val orderTypeResult = orderTypeRepository.getOrderTypes()
            val orderTypeId = orderTypeResult.getOrDefault(emptyList()).find { it.name == "บนบาน" }?.id
            val request = OrderRequest.Vow(
                VowOrderRequest(
                    deadline = "",
                    note = "",
                    vow = "",
                    price = 0.0,
                    items = emptyList(),
                    packageId = _state.value.selectedPackageId ?: "",
                    serviceId = _serviceId,
                    orderTypeID = orderTypeId ?: ""
                )
            )
            orderRepository.setOrderRequest(request)
        }
    }

    fun createFulfillOrderRequest() {
        viewModelScope.launch {
            val orderTypeResult = orderTypeRepository.getOrderTypes()
            val orderTypeId = orderTypeResult.getOrDefault(emptyList()).find { it.name == "บนบาน" }?.id
            val request = OrderRequest.Fulfill(
                FulfillOrderRequest(
                    price = 0.0,
                    items = emptyList(),
                    packageId = _state.value.selectedPackageId ?: "",
                    serviceId = _serviceId,
                    vowRecordID = _state.value.vowRecord?.id ?: "",
                    orderTypeID = orderTypeId ?: ""
                )
            )
            orderRepository.setOrderRequest(request)
        }
    }

    private fun getServiceDetail() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = serviceRepository.getService(_serviceId)
            result.fold(
                onSuccess = { service ->
                    val mappedService = service.copy(categories = CategoryUtils.mapCategoriesIcon(service.categories))
                    _state.update { it.copy(service = mappedService, isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
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
                    _state.update { it.copy(errorMessage = error.message, isReviewsLoading = false) }
                }
            )
        }
    }

    private fun getUnFulfilledVowRecords() {
        viewModelScope.launch {
            val result = vowRecordRepository.getUnFulfilledVowRecordsByService(_serviceId)
            result.fold(
                onSuccess = { vowRecord ->
                    _state.update { it.copy(hasUnFulfilledVowRecords = true, vowRecord = vowRecord) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message) }
                }
            )

        }
    }

}