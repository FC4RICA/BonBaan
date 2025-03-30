package com.fc4rica.bonbaan.ui

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val authRepository: AuthRepository,
    private val userPreferences: DataStore<UserPreferences>
) : ViewModel() {
    private val _isAuthenticated = MutableStateFlow<Boolean?>(null)
    val isAuthenticated: StateFlow<Boolean?> = _isAuthenticated.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            userPreferences.data.map { it.token }
                .onEach { token -> _isAuthenticated.value = !token.isNullOrEmpty() }
                .launchIn(viewModelScope)
        }

        fun logout() {
            viewModelScope.launch {
                _isAuthenticated.value = false
                authRepository.logout()
            }
        }
    }
}