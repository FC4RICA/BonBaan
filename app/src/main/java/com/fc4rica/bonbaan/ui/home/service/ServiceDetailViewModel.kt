package com.fc4rica.bonbaan.ui.home.service

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Review
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.repository.ReviewRepository
import com.fc4rica.bonbaan.domain.repository.ServiceRepository
import com.fc4rica.bonbaan.domain.repository.VowRecordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ServiceDetailUiState(
    val service: Service? = null,
    val reviews: List<Review> = emptyList(),
    val isReviewsLoading: Boolean = false,
    val hasUnFulfilledVowRecords: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ServiceDetailViewModel(
    private val serviceRepository: ServiceRepository,
    private val reviewRepository: ReviewRepository,
    private val vowRecordRepository: VowRecordRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(ServiceDetailUiState())
    val state = _state.asStateFlow()

    private val serviceId = savedStateHandle.get<String>("serviceId") ?: ""

    init {
        getUnFulfilledVowRecords()
        getServiceDetail()
        getReviews()
    }

    private fun getServiceDetail() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = serviceRepository.getService(serviceId)
            result.fold(
                onSuccess = { service ->
                    _state.update { it.copy(service = service, isLoading = false) }
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
            val result = reviewRepository.getReviewsByService(serviceId)
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
            val result = vowRecordRepository.getUnFulfilledVowRecordsByService(serviceId)
            result.fold(
                onSuccess = { vowRecord ->
                    _state.update { it.copy(hasUnFulfilledVowRecords = true) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message) }
                }
            )

        }
    }

}