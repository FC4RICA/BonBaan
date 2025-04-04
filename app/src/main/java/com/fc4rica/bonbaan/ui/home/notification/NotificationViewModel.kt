package com.fc4rica.bonbaan.ui.home.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc4rica.bonbaan.domain.model.Notification
import com.fc4rica.bonbaan.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class NotificationUiState(
    val notifications: List<Notification> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

class NotificationViewModel(
    private val notificationRepository: NotificationRepository
) : ViewModel() {
    private val _state = MutableStateFlow(NotificationUiState())
    val state = _state.asStateFlow()

    init {
        getNotifications()
    }

    private fun getNotifications() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = notificationRepository.getNotifications()
            result.fold(
                onSuccess = { notifications ->
                    _state.update { it.copy(notifications = notifications, isLoading = false) }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }
    }

    fun markAsRead(id: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = notificationRepository.markAsRead(id)
            result.fold(
                onSuccess = {
                    _state.update { current ->
                        current.copy(notifications = current.notifications.map { notification ->
                            if (notification.id == id) notification.copy(isRead = true) else notification
                        }, isLoading = false)
                    }
                },
                onFailure = { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
            )
        }
    }
}