package com.fc4rica.bonbaan.ui.home.feed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.model.request.PaginationRequest
import com.fc4rica.bonbaan.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FilteredServiceUiState(
    val query: String = "",
    val sortType: SortType = SortType.Recommend,
    val services: List<Service> = emptyList(),
    val isLoading: Boolean = false,
    val isPaginating: Boolean = false,
    val isEndReached: Boolean = false,
    val errorMessage: String? = null
)

sealed class SortType {
    data object Recommend : SortType()
    data object Popular : SortType()
    data object Rating : SortType()
}

class FilteredServiceViewModel(
    private val serviceRepository: ServiceRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _query = savedStateHandle.get<String>("query") ?: ""

    private val _state = MutableStateFlow(FilteredServiceUiState(query = _query))
    val state = _state.asStateFlow()

    private var currentPage = 1
    private val pageSize = 10

    init {
        getServices()
    }

    fun setSortType(newSortType: SortType) {
        if (_state.value.sortType == newSortType) return
        _state.update { it.copy(sortType = newSortType, services = emptyList(), isEndReached = false) }
        getServices()
    }

    private fun getServices() {
        if (_state.value.isLoading) return
        currentPage = 1
        fetchServices(clearPrevious = true)
    }

    fun getMoreService() {
        if (_state.value.isLoading || _state.value.isPaginating || _state.value.isEndReached) return
        fetchServices()
    }

    private fun fetchServices(clearPrevious: Boolean = false) {
        if (clearPrevious) {
            _state.update { it.copy(isLoading = true) }
        } else {
            _state.update { it.copy(isPaginating = true) }
        }

        viewModelScope.launch {
            val paginationRequest = PaginationRequest(
                page = currentPage,
                pageSize = pageSize,
                search = _state.value.query,
                orderBy = if (_state.value.sortType is SortType.Rating) "rate" else null
            )

            val result = when (_state.value.sortType) {
                SortType.Recommend ->
                    serviceRepository.getRecommendedServices(paginationRequest)

                SortType.Popular ->
                    serviceRepository.getBestSellerServices(paginationRequest)

                SortType.Rating ->
                    serviceRepository.getServices(paginationRequest)
            }
            result.fold(
                onSuccess = { newServices ->
                    _state.update {
                        it.copy(
                            services = if (clearPrevious) newServices else it.services + newServices,
                            isLoading = false,
                            isPaginating = false,
                            isEndReached = newServices.size < pageSize
                        )
                    }
                    currentPage++
                },
                onFailure = { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isPaginating = false,
                            errorMessage = error.message
                        )
                    }
                }
            )
        }
    }
}