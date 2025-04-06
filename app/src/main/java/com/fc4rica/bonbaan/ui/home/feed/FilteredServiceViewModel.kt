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
    object Recommend : SortType()
    object Popular : SortType()
    object Rating : SortType()
}

class FilteredServiceViewModel(
    private val serviceRepository: ServiceRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val query = savedStateHandle.get<String>("query") ?: ""

    private val _state = MutableStateFlow(FilteredServiceUiState(query = query))
    val state = _state.asStateFlow()

    private var currentPage = 1
    private val pageSize = 10

    init {
        getServicess()
    }

    fun getMoreService() {
        if (_state.value.isLoading || _state.value.isPaginating || _state.value.isEndReached) return

        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = when (_state.value.sortType) {
                SortType.Recommend -> // TODO add search query
                    serviceRepository.getRecommendedServices(
                        page = currentPage,
                        pageSize = pageSize
                    )

                SortType.Popular -> // TODO add search query
                    serviceRepository.getPopularServices(page = currentPage, pageSize = pageSize)

                SortType.Rating ->
                    serviceRepository.getServices(
                        PaginationRequest(
                            page = currentPage,
                            pageSize = pageSize,
                            orderBy = "rate",
                            search = query
                        )
                    )
            }
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

    fun getServicess() {
        viewModelScope.launch {
            val result = when (_state.value.sortType) {
                SortType.Recommend -> // TODO add search query
                    serviceRepository.getRecommendedServices(
                        page = currentPage,
                        pageSize = pageSize
                    )

                SortType.Popular -> // TODO add search query
                    serviceRepository.getPopularServices(page = currentPage, pageSize = pageSize)

                SortType.Rating ->
                    serviceRepository.getServices(
                        PaginationRequest(
                            page = currentPage,
                            pageSize = pageSize,
                            orderBy = "rate",
                            search = query
                        )
                    )
            }
            result.fold(
                onSuccess = { services ->
                    _state.update { it.copy(services = services) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message) }
                }
            )
        }
    }
}