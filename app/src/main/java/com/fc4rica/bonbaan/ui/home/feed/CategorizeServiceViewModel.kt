package com.fc4rica.bonbaan.ui.home.feed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Category
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.repository.CategoryRepository
import com.fc4rica.bonbaan.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CategorizeServiceUiState(
    val services: List<Service> = emptyList(),
    val category: Category? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class CategorizeServiceViewModel(
    private val serviceRepository: ServiceRepository,
    private val categoryRepository: CategoryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val categoryId: String = checkNotNull(savedStateHandle["categoryId"])

    private val _state = MutableStateFlow(CategorizeServiceUiState())
    val state = _state.asStateFlow()

    init {
        getServicesAndCategory()
    }

    private fun getServicesAndCategory() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val categoryResult = categoryRepository.getCategory(categoryId)
            if (categoryResult.isFailure) {
                return@launch _state.update {
                    it.copy(
                        errorMessage = categoryResult.exceptionOrNull()?.message,
                        isLoading = false
                    )
                }
            }

            _state.update { it.copy(category = categoryResult.getOrNull())}

            val serviceResult = serviceRepository.getServicesByCategory(categoryId)
            serviceResult.fold(
                onSuccess = { services ->
                    _state.update {
                        it.copy(
                            services = services,
                            isLoading = false
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
    }
}