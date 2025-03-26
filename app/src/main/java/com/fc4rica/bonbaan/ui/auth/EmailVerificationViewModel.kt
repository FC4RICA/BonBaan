package com.fc4rica.bonbaan.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.repository.RegisterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EmailVerificationUiState(
    var isCodeValid: Boolean = false,
    var isOtpSent: Boolean = false,
    var isLoading: Boolean = false,
    var errorMessage: String? = null
)

class EmailVerificationViewModel(
    private val registerRepository: RegisterRepository
) : ViewModel() {
    private val _state = MutableStateFlow(EmailVerificationUiState())
    val state = _state.asStateFlow()

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
                    _state.update { it.copy(isLoading = false) }
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
                onSuccess = { user ->
                    _state.update { it.copy(isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
            )
        }
    }
}