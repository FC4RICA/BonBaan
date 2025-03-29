package com.fc4rica.bonbaan.di

import com.fc4rica.bonbaan.data.repository.AuthRepositoryImpl
import com.fc4rica.bonbaan.data.repository.RegisterRepositoryImpl
import com.fc4rica.bonbaan.domain.repository.AuthRepository
import com.fc4rica.bonbaan.domain.repository.RegisterRepository
import com.fc4rica.bonbaan.ui.auth.LoginViewModel
import com.fc4rica.bonbaan.ui.auth.register.EmailVerificationViewModel
import com.fc4rica.bonbaan.ui.auth.register.InputEmailViewModel
import com.fc4rica.bonbaan.ui.auth.register.PasswordSetupViewModel
import com.fc4rica.bonbaan.ui.auth.register.PersonalInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::RegisterRepositoryImpl) bind RegisterRepository::class
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class

    viewModelOf(::LoginViewModel)
    viewModelOf(::InputEmailViewModel)
    viewModelOf(::PersonalInfoViewModel)
    viewModelOf(::PasswordSetupViewModel)
    viewModelOf(::EmailVerificationViewModel)
}

fun initKoin() {
    startKoin{
        modules(networkModule, appModule)
    }
}