package com.fc4rica.bonbaan.ui.auth

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.fc4rica.bonbaan.domain.repository.RegisterRepository
import com.fc4rica.bonbaan.utils.isValidName
import com.fc4rica.bonbaan.utils.isValidPhone
import com.fc4rica.bonbaan.utils.isValidUsername
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PersonalInfoUiState(
    var name: String = "",

    var isNameValid: Boolean = false,
    var isPhoneValid: Boolean = false,
    var isUsernameValid: Boolean = false,

    var nameError: String? = null,
    var phoneError: String? = null,
    var usernameError: String? = null,
)

class PersonalInfoViewModel(
    private val registerRepository: RegisterRepository
) : ViewModel() {
    private val _state = MutableStateFlow(PersonalInfoUiState())
    val state = _state.asStateFlow()

    val registerRequest = registerRepository.registerRequest

    fun updateName(name: String) {
        _state.update { it.copy(name = name) }
    }

    fun updatePhone(phone: String) {
        if (phone.isDigitsOnly() && phone.length <= 10) {
            registerRepository.updateRequest { copy(phone = phone) }
        }
    }

    fun updateUsername(username: String) {
        registerRepository.updateRequest { copy(username = username) }
    }

    fun submitPersonalInfo(): Boolean {
        val (isNameValid, nameError) = state.value.name.isValidName()
        val (isPhoneValid, phoneError) = registerRequest.value.phone.isValidPhone()
        val (isUsernameValid, usernameError) = registerRequest.value.username.isValidUsername()

        if (isNameValid) registerRepository.updateRequest {
            copy(
                firstname = state.value.name.split(" ")[0],
                lastname = state.value.name.split(" ")[1]
            )
        }

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

}