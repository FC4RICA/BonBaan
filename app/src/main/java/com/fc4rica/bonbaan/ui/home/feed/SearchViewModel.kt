package com.fc4rica.bonbaan.ui.home.feed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.model.request.PaginationRequest
import com.fc4rica.bonbaan.domain.repository.ServiceRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SearchUiState(
    val query: String = "",
    val searchResult: List<Service> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val serviceRepository: ServiceRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _query = savedStateHandle.get<String>("query") ?: ""

    private val _state = MutableStateFlow(SearchUiState(query = _query))
    val state = _state.asStateFlow()

    private val _queryFlow = MutableStateFlow(_query)

    init {
        viewModelScope.launch {
            _queryFlow
                .debounce(1000L)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isNotBlank()) {
                        search(query)
                    } else {
                        _state.update { it.copy(searchResult = emptyList()) }
                    }
                }
        }
    }

    fun updateSearchQuery(query: String) {
        _state.update { it.copy(query = query) }
        _queryFlow.value = query
    }

    private fun search(query: String) {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = serviceRepository.getServices(
                PaginationRequest(
                    page = 1,
                    search = query
                )
            )
            result.fold(
                onSuccess = { services ->
                    _state.update { it.copy(searchResult = services, isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }
    }
}