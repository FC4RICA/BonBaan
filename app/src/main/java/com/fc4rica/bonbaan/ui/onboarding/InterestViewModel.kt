package com.fc4rica.bonbaan.ui.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Category
import com.fc4rica.bonbaan.domain.model.request.InterestRequest
import com.fc4rica.bonbaan.domain.repository.CategoryRepository
import com.fc4rica.bonbaan.domain.repository.InterestRepository
import com.fc4rica.bonbaan.utils.CategoryUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class InterestUiState(
    val categories: List<Category> = emptyList(),
    val selectedInterests: List<String> = emptyList(),
    val isSuccessful: Boolean = false,
    val errorMessage: String? = null
)

class InterestViewModel(
    private val categoryRepository: CategoryRepository,
    private val interestRepository: InterestRepository
) : ViewModel() {
    private val _state = MutableStateFlow(InterestUiState())
    val state = _state.asStateFlow()

    init {
        getInterests()
    }

    private fun getInterests() {
        Log.d("InterestViewModel", "getInterests called")
        viewModelScope.launch {
            val result = categoryRepository.getCategories()
            Log.d("InterestViewModel", "Result: $result")
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
        }
    }

    fun selectInterest(interestId: String) {
        Log.d("InterestViewModel", "selectInterest called with interestId: $interestId")
        val currentSelectedInterests = _state.value.selectedInterests
        if (currentSelectedInterests.contains(interestId)) {
            _state.update { it.copy(selectedInterests = currentSelectedInterests - interestId) }
            return
        }

        _state.update { it.copy(selectedInterests = currentSelectedInterests + interestId) }
        Log.d("InterestViewModel", "Selected Interests: ${_state.value.selectedInterests}")
    }

    fun submitInterests() {
        Log.d("InterestViewModel", "submitInterests called")
        viewModelScope.launch {
            Log.d("InterestViewModel", "Selected Interests: ${InterestRequest(_state.value.selectedInterests)}")
            val result = interestRepository.addInterest(
                InterestRequest(_state.value.selectedInterests)
            )
            Log.d("InterestViewModel", "Result: $result")
            result.fold(
                onSuccess = {
                    _state.update { it.copy(isSuccessful = true) }
                },
                onFailure = { error ->
                    Log.d("InterestViewModel", "Error: $error")
                    _state.update { it.copy(errorMessage = error.message) }
                }
            )
        }
    }
}