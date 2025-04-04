package com.fc4rica.bonbaan.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Review
import com.fc4rica.bonbaan.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PreviousReviewsUiState(
    val reviews: List<Review> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

class PreviousReviewsViewModel(
    private val reviewRepository: ReviewRepository
) : ViewModel() {
    private val _state = MutableStateFlow(PreviousReviewsUiState())
    val state = _state.asStateFlow()

    init {
        getPreviousReviews()
    }

    private fun getPreviousReviews() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = reviewRepository.getReviews()
            result.fold(
                onSuccess = { reviews ->
                    val sortedReviews = reviews.sortedByDescending { it.createdAt }
                    _state.update { it.copy(reviews = sortedReviews, isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }
        }
    }