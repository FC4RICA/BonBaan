package com.fc4rica.bonbaan.ui.home.vow_record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.VowRecord
import com.fc4rica.bonbaan.domain.repository.VowRecordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class VowRecordUiState(
    val vowRecords: List<VowRecord> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

class VowRecordViewModel(
    private val vowRecordRepository: VowRecordRepository
) : ViewModel() {
    private val _state = MutableStateFlow(VowRecordUiState())
    val state = _state.asStateFlow()

    init {
        getVowRecords()
    }

    private fun getVowRecords() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = vowRecordRepository.getVowRecords()
            result.fold(
                onSuccess = { vowRecords ->
                    _state.update { it.copy(vowRecords = vowRecords, isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }
    }
}