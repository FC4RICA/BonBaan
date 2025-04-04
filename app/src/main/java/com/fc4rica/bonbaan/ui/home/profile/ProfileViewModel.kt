package com.fc4rica.bonbaan.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileUiState(
    val user: User? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

class ProfileViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
        getProfile()
    }

    private fun getProfile() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = authRepository.getProfile()
            result.fold(
                onSuccess = { user ->
                    _state.update { it.copy(user = user, isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }
    }
}