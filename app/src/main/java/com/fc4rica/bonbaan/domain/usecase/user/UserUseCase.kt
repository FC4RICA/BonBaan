package com.fc4rica.bonbaan.domain.usecase.user

import com.fc4rica.bonbaan.domain.repository.UserRepository

class UserUseCase(userRepository: UserRepository) {
    val register = RegisterUseCase(userRepository)
    val login = LoginUseCase(userRepository)
    val sendOtp = OtpUseCase(userRepository)
}