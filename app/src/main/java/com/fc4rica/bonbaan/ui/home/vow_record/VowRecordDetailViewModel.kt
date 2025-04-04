package com.fc4rica.bonbaan.ui.home.vow_record

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.VowRecord
import com.fc4rica.bonbaan.domain.repository.VowRecordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class VowRecordDetailUiState(
    val vowRecord: VowRecord? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

class VowRecordDetailViewModel(
    private val vowRecordRepository: VowRecordRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(VowRecordDetailUiState())
    val state = _state.asStateFlow()

    private val vowRecordId: String = checkNotNull(savedStateHandle["vowRecordId"])

    init {
        getVowRecordDetail()
    }

    private fun getVowRecordDetail() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = vowRecordRepository.getVowRecord(vowRecordId)
            result.fold(
                onSuccess = { vowRecord ->
                    _state.update { it.copy(vowRecord = vowRecord, isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }
    }
}