package com.fc4rica.bonbaan.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.request.LoginRequest
import com.fc4rica.bonbaan.domain.repository.AuthRepository
import com.fc4rica.bonbaan.domain.repository.InterestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val emailOrUsername: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false,
    val isFirstTime: Boolean? = null
)

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val interestRepository: InterestRepository
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
            val result = authRepository.login(
                LoginRequest(
                    state.value.emailOrUsername,
                    state.value.password
                )
            )
            result.fold(
                onSuccess = {
                    val interestResult = interestRepository.getInterests()

                    interestResult.fold(
                        onSuccess = { interests ->
                            _state.update { it.copy(isLoggedIn = true, isFirstTime = interests.isEmpty()) }
                        },
                        onFailure = { error ->
                            _state.update { it.copy(errorMessage = error.message) }
                        }
                    )

                    _state.update { it.copy(errorMessage = null) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง" + error.message) }
                }
            )
        }
    }
}