package com.fc4rica.bonbaan.ui

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val authRepository: AuthRepository,
    private val userPreferences: DataStore<UserPreferences>
) : ViewModel() {
    private val _isAuthenticated = MutableStateFlow<Boolean?>(null)
    val isAuthenticated: StateFlow<Boolean?> = userPreferences.data
        .map { it.token.isNullOrEmpty().not() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun logout() {
        viewModelScope.launch {
            _isAuthenticated.value = false
            authRepository.logout()
        }
    }
}