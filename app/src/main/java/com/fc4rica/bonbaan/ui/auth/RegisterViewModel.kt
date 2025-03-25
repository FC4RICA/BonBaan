package com.fc4rica.bonbaan.ui.auth

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.User
import com.fc4rica.bonbaan.domain.model.request.RegisterRequest
import com.fc4rica.bonbaan.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.fc4rica.bonbaan.utils.isValidEmail
import com.fc4rica.bonbaan.utils.isValidName
import com.fc4rica.bonbaan.utils.isValidPhone
import com.fc4rica.bonbaan.utils.isValidUsername
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
    var isNameValid: Boolean = false,
    var isPhoneValid: Boolean = false,
    var isUsernameValid: Boolean = false,
    var isPasswordValid: Boolean = false,
    var isConfirmPasswordValid: Boolean = false,
    var isCodeValid: Boolean = false,

    var emailError: String? = null,
    var nameError: String? = null,
    var phoneError: String? = null,
    var usernameError: String? = null,
    var passwordError: String? = null,
    var confirmPasswordError: String? = null,

    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var user: User? = null
)

class RegisterViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state.asStateFlow()

    fun updateField(field: String, value: String) {
        if (state.value.isLoading) return
        _state.update {
            when (field) {
                "name" -> it.copy(name = value)
                "username" -> it.copy(username = value)
                "email" -> it.copy(email = value)
                "phone" -> if (value.isDigitsOnly() && value.length < 10) it.copy(phone = value) else it
                "password" -> it.copy(password = value)
                "confirmPassword" -> it.copy(confirmPassword = value)
                "code" -> it.copy(code = value)
                else -> it
            }
        }
    }

    fun submitEmail(): Boolean {
        val isValid = state.value.email.isValidEmail()
        _state.update {
            it.copy(isEmailValid = isValid, emailError = if (isValid) null else "อีเมลไม่ถูกต้อง")
        }
        return isValid
    }

    fun submitPersonalInfo(): Boolean {
        val (isNameValid, nameError) = state.value.name.isValidName()
        val (isPhoneValid, phoneError) = state.value.phone.isValidPhone()
        val (isUsernameValid, usernameError) = state.value.username.isValidUsername()

        _state.update {
            it.copy(
                isNameValid = isNameValid,
                nameError = if (isNameValid) null else nameError,
                isPhoneValid = isPhoneValid,
                phoneError = if (isPhoneValid) null else phoneError,
                isUsernameValid = isUsernameValid,
                usernameError = if (isUsernameValid) null else usernameError
            )
        }
        return isNameValid && isPhoneValid && isUsernameValid
    }

    fun sendOTP() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            val result = userRepository.sendOtp(state.value.email)
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

            val request = RegisterRequest(
                username = state.value.username,
                firstname = state.value.name.split(" ")[0],
                lastname = state.value.name.split(" ")[1],
                email = state.value.email,
                phone = state.value.phone,
                password = state.value.password,
                code = state.value.code
            )
            val result = userRepository.register(request)
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