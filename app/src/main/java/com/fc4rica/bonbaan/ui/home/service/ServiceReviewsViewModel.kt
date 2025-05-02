package com.fc4rica.bonbaan.ui.home.service

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Review
import com.fc4rica.bonbaan.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ServiceReviewUiState(
    val reviews: List<Review> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ServiceReviewsViewModel(
    private val reviewRepository: ReviewRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(ServiceReviewUiState())
    val state = _state.asStateFlow()

    private val _serviceId = savedStateHandle.get<String>("serviceId") ?: ""

    init {
        getReviews()
    }

    private fun getReviews() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = reviewRepository.getReviewsByService(_serviceId)
            result.fold(
                onSuccess = { reviews ->
                    _state.update { it.copy(reviews = reviews, isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }
    }
}