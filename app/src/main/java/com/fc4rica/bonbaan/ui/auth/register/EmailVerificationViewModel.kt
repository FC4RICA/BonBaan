package com.fc4rica.bonbaan.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.repository.RegisterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EmailVerificationUiState(
    var isCodeValid: Boolean? = null,
    var isOtpSent: Boolean = false,
    var isLoading: Boolean = false,
    var errorMessage: String? = null
)

class EmailVerificationViewModel(
    private val registerRepository: RegisterRepository
) : ViewModel() {
    private val _state = MutableStateFlow(EmailVerificationUiState())
    val state = _state.asStateFlow()

    private val _navigateToLogin = MutableSharedFlow<Unit>()
    val navigateToLogin = _navigateToLogin.asSharedFlow()

    val registerRequest = registerRepository.registerRequest

    fun updateOtp(code: String) {
        if (state.value.isLoading) return
        registerRepository.updateRequest { copy(code = code) }
    }

    private val _otpCooldown = MutableStateFlow(0)
    val otpCooldown = _otpCooldown.asStateFlow()

    private var countdownJob: Job? = null

    fun onEnterEmailVerificationScreen() {
        if (!_state.value.isOtpSent) {
            sendOTP()
            _state.update { it.copy(isOtpSent = true) }
        }
    }

    fun sendOTP() {
        if (_otpCooldown.value > 0) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            val result = registerRepository.sendOtp()
            result.fold(
                onSuccess = {
                    _state.update { it.copy(isLoading = false, errorMessage = null) }
                    startOtpCooldown()
                },
                onFailure = { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
            )
        }
    }

    private fun startOtpCooldown(seconds : Int = 30) {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            for (time in seconds downTo 0) {
                _otpCooldown.value = time
                delay(1000L)
            }
        }
    }

    fun registerUser() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            val result = registerRepository.register()
            result.fold(
                onSuccess = {
                    _state.update { it.copy(isLoading = false, isCodeValid = true) }
                    _navigateToLogin.emit(Unit)
                },
                onFailure = { error ->
                    _state.update { it.copy(isLoading = false, isCodeValid = false, errorMessage = error.message) }
                }
            )
        }
    }
}