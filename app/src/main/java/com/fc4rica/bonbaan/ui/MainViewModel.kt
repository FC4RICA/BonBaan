package com.fc4rica.bonbaan.ui

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.domain.repository.AuthRepository
import com.fc4rica.bonbaan.domain.repository.InterestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainViewModel(
    private val authRepository: AuthRepository,
    private val interestRepository: InterestRepository,
    private val userPreferences: DataStore<UserPreferences>
) : ViewModel() {
    private val _isAuthenticated = MutableStateFlow<Boolean?>(null)
    val isAuthenticated: StateFlow<Boolean?> get() = _isAuthenticated.asStateFlow()

    private val _hasSelectedInterests = MutableStateFlow<Boolean?>(null)
    val hasSelectedInterests: StateFlow<Boolean?> get() = _hasSelectedInterests.asStateFlow()

    init {
        checkAuthentication()
    }

    private fun checkAuthentication() {
        viewModelScope.launch {
            userPreferences.data.firstOrNull()?.let { prefs ->
                val token = prefs.token
                if (token.isNullOrEmpty()) {
                    _isAuthenticated.value = false
                    return@launch
                }

                // Validate token by fetching user profile
                val result = authRepository.getProfile()
                _isAuthenticated.value = result.isSuccess

                if (result.isSuccess) {
                    _isAuthenticated.value = true
                    checkUserInterests()
                } else {
                    _isAuthenticated.value = false
                }
            } ?: run {
                _isAuthenticated.value = false
            }
        }
    }

    private fun checkUserInterests() {
        viewModelScope.launch {
            val result = interestRepository.getInterests()
            _hasSelectedInterests.value = result.getOrNull()?.isNotEmpty() == true
        }
    }

    fun logout() {
        viewModelScope.launch {
            _isAuthenticated.value = false
            _hasSelectedInterests.value = null
            authRepository.logout()
        }
    }
}