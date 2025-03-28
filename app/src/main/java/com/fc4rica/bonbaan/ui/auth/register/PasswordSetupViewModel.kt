package com.fc4rica.bonbaan.ui.auth.register

import androidx.lifecycle.ViewModel
import com.fc4rica.bonbaan.domain.repository.RegisterRepository
import com.fc4rica.bonbaan.utils.isValidConfirmPassword
import com.fc4rica.bonbaan.utils.isValidPassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PasswordSetupUiState(
    var confirmPassword: String = "",
    var isPasswordValid: Boolean = false,
    var isConfirmPasswordValid: Boolean = false,
    var passwordError: String? = null,
    var confirmPasswordError: String? = null,
)

class PasswordSetupViewModel(
    private val registerRepository: RegisterRepository
) : ViewModel() {
    private val _state = MutableStateFlow(PasswordSetupUiState())
    val state = _state.asStateFlow()

    val registerRequest = registerRepository.registerRequest

    fun updatePassword(password: String) {
        registerRepository.updateRequest { copy(password = password) }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _state.update { it.copy(confirmPassword = confirmPassword) }
    }

    fun submitPassword(): Boolean {
        val (isPasswordValid, passwordError) = registerRequest.value.password.isValidPassword()
        val (isConfirmPasswordValid, confirmPasswordError) = state.value.confirmPassword.isValidConfirmPassword(registerRequest.value.password)

        _state.update {
            it.copy(
                isPasswordValid = isPasswordValid,
                passwordError = if (isPasswordValid) null else passwordError,
                isConfirmPasswordValid = isConfirmPasswordValid,
                confirmPasswordError = if (isConfirmPasswordValid) null else confirmPasswordError
            )
        }
        return isPasswordValid && isConfirmPasswordValid
    }
}