package com.fc4rica.bonbaan.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.request.LoginRequest
import com.fc4rica.bonbaan.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val emailOrUsername: String = "",
    val password: String = "",
    val errorMessage: String? = null,
)

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    fun updateEmailOrUsername(name: String) {
        _state.update { it.copy(emailOrUsername = name) }
    }

    fun updatePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun submitLogin() {
        if (state.value.emailOrUsername.isEmpty() || state.value.password.isEmpty()) {
            _state.update { it.copy(errorMessage = "กรุณากรอกข้อมูลให้ครบถ้วน") }
            return
        }

        viewModelScope.launch {
            val result = authRepository.login(LoginRequest(state.value.emailOrUsername, state.value.password))
            result.fold(
                onSuccess = { authResponse ->
                    _state.update { it.copy(errorMessage = null) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message) }
                }
            )
        }
    }
}