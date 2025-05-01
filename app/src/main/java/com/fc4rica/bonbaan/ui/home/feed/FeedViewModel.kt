package com.fc4rica.bonbaan.ui.home.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Category
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.model.request.PaginationRequest
import com.fc4rica.bonbaan.domain.repository.CategoryRepository
import com.fc4rica.bonbaan.domain.repository.ServiceRepository
import com.fc4rica.bonbaan.utils.CategoryUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FeedUiState(
    val services: List<Service> = emptyList(),
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val isPaginating: Boolean = false,
    val isEndReached: Boolean = false,
    val errorMessage: String? = null
)

class FeedViewModel(
    private val serviceRepository: ServiceRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _state = MutableStateFlow(FeedUiState())
    val state = _state.asStateFlow()

    private val _isCategoryLoading = MutableStateFlow(false)
    private val _isServiceLoading = MutableStateFlow(false)

    private var currentPage = 1
    private val pageSize = 10

    init {
        getCategories()
        getRecommendedService()

        viewModelScope.launch {
            combine(_isCategoryLoading, _isServiceLoading) { profileLoading, serviceLoading ->
                profileLoading || serviceLoading
            }.collect { isLoading ->
                _state.update { it.copy(isLoading = isLoading) }
            }
        }
    }

    fun loadMoreServices() {
        if (_state.value.isPaginating || _state.value.isEndReached) return

        _state.update { it.copy(isPaginating = true) }

        viewModelScope.launch {
            val result = serviceRepository.getRecommendedServices(PaginationRequest(
                page = currentPage + 1, pageSize = pageSize
            ))
            result.fold(
                onSuccess = { newServices ->
                    val updatedList = _state.value.services + newServices
                    _state.update {
                        it.copy(
                            services = updatedList,
                            isPaginating = false,
                            isEndReached = newServices.size < pageSize
                        )
                    }
                    currentPage++
                },
                onFailure = { error ->
                    _state.update {
                        it.copy(errorMessage = error.message, isPaginating = false)
                    }
                }
            )
        }
    }

    private fun getRecommendedService() {
        _isServiceLoading.value = true
        currentPage = 1
        _state.update { it.copy(isEndReached = false) }

        viewModelScope.launch {
            val result = serviceRepository.getRecommendedServices(PaginationRequest(
                page = currentPage, pageSize = pageSize
            ))
            result.fold(
                onSuccess = { services ->
                    _state.update { it.copy(services = services) }
                },
                onFailure = { error ->
                    _state.update {
                        it.copy(errorMessage = error.message)
                    }
                }
            )
            _isServiceLoading.value = false
        }
    }

    private fun getCategories() {
        _isCategoryLoading.value = true
        viewModelScope.launch {
            val result = categoryRepository.getCategories()
            result.fold(
                onSuccess = { categories ->
                    val mappedCategory = CategoryUtils.mapCategoriesIcon(categories)
                    _state.update { it.copy(categories = mappedCategory) }
                },
                onFailure = { error ->
                    _state.update {
                        it.copy(errorMessage = error.message)
                    }
                }
            )
            _isCategoryLoading.value = false
        }
    }
}