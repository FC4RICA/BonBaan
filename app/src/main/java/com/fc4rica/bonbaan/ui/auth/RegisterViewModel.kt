package com.fc4rica.bonbaan.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.usecase.user.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import com.fc4rica.bonbaan.utils.isValidEmail
import kotlinx.coroutines.launch

data class RegisterUiState(
    var name: String = "",
    var username: String = "",
    var email: String = "",
    var phone: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var code: String = "",

    var isEmailValid: Boolean = false,
    var isPhoneValid: Boolean = false,
    var isPasswordValid: Boolean = false,
    var isConfirmPasswordValid: Boolean = false,

    var emailError: String? = null,
    var phoneError: String? = null,
    var passwordError: String? = null,
    var confirmPasswordError: String? = null,

    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var user: User? = null
)

class RegisterViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            state.distinctUntilChangedBy { it.email }
                .map {
                    val isValid = it.email.isValidEmail()
                    isValid to if (isValid) null else "Invalid email"
                }.onEach { (isValid, errorMessage) ->
                    _state.update { it.copy(isEmailValid = isValid, emailError = errorMessage) }
                }

            state.distinctUntilChangedBy { it.phone }.map { it.phone.length == 10 }
                .onEach { isPhoneValid -> _state.update { it.copy(isPhoneValid = isPhoneValid) } }

            state.distinctUntilChangedBy { it.password }.map { it.password.length >= 8 }
                .onEach { isPasswordValid -> _state.update { it.copy(isPasswordValid = isPasswordValid) } }

            state.distinctUntilChangedBy { it.confirmPassword }
                .map { it.password == it.confirmPassword }
                .onEach { isConfirmPasswordValid -> _state.update { it.copy(isConfirmPasswordValid = isConfirmPasswordValid) } }
        }
    }

    fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun sendOTP() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            val result = userUseCase.sendOtp.execute(state.value.email)
            result.fold(
                onSuccess = {
                    _state.update { it.copy(isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
            )
        }
    }

    fun registerUser() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            val result = userUseCase.register.execute(state.value)
            result.fold(
                onSuccess = { user ->
                    _state.update { it.copy(isLoading = false, user = user) }
                },
                onFailure = { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
            )
        }
    }
}