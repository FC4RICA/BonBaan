package com.fc4rica.bonbaan.ui.auth

import androidx.lifecycle.ViewModel
import com.fc4rica.bonbaan.domain.repository.RegisterRepository
import com.fc4rica.bonbaan.utils.isValidEmail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class InputEmailUiState(
    var isEmailValid: Boolean = false,
    var emailError: String? = null,
)

class InputEmailViewModel(
    private val registerRepository: RegisterRepository
) : ViewModel() {
    private val _state = MutableStateFlow(InputEmailUiState())
    val state = _state.asStateFlow()

    val registerRequest = registerRepository.registerRequest

    fun updateEmail(email: String) {
        registerRepository.updateRequest { copy(email = email) }
    }

    fun submitEmail(): Boolean {
        val isValid = registerRequest.value.email.isValidEmail()
        _state.update {
            it.copy(isEmailValid = isValid, emailError = if (isValid) null else "อีเมลไม่ถูกต้อง")
        }
        return isValid
    }
}