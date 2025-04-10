package com.fc4rica.bonbaan.ui.home.service

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Order
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.model.request.ReviewRequest
import com.fc4rica.bonbaan.domain.repository.OrderRepository
import com.fc4rica.bonbaan.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ReviewUiState(
    val rating: Int = 0,
    val detail: String = "",
    val service: Service? = null,
    val order: Order? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val createdReviewId: String? = null,
    val isReviewValid: Boolean = false,
)

class ReviewOrderViewModel(
    private val orderRepository: OrderRepository,
    private val reviewRepository: ReviewRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(ReviewUiState())
    val state = _state.asStateFlow()

    init {
        val orderId: String = checkNotNull(savedStateHandle["orderId"])
        getOrderAndService(orderId)
    }

    fun updateRating(rating: Int) {
        if (rating in 1..5)
            _state.update { it.copy(rating = rating) }
    }

    fun updateDetail(detail: String) {
        _state.update { it.copy(detail = detail) }
    }

    fun submitReview() {
        val isReviewValid = state.value.rating != 0 && state.value.detail.isNotEmpty()
        _state.update { it.copy(isReviewValid = isReviewValid) }
        if (!isReviewValid) return

        val reviewRequest = ReviewRequest(
            serviceId = state.value.service!!.id,
            rating = state.value.rating,
            detail = state.value.detail,
            orderId = state.value.order!!.id
        )

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = reviewRepository.createReview(reviewRequest)
            result.fold(
                onSuccess = { review ->
                    _state.update {
                        it.copy(
                            order = null,
                            service = null,
                            rating = 0,
                            detail = "",
                            isLoading = false,
                            createdReviewId = review.id,
                        )
                    }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }
    }

    private fun getOrderAndService(orderId: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = orderRepository.getOrder(orderId)
            result.fold(
                onSuccess = { order ->
                    _state.update {
                        it.copy(
                            order = order,
                            service = order.service,
                            isLoading = false
                        )
                    }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }
    }
}