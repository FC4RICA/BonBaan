package com.fc4rica.bonbaan.di

import com.fc4rica.bonbaan.data.repository.RegisterRepositoryImpl
import com.fc4rica.bonbaan.domain.repository.RegisterRepository
import com.fc4rica.bonbaan.ui.auth.EmailVerificationViewModel
import com.fc4rica.bonbaan.ui.auth.InputEmailViewModel
import com.fc4rica.bonbaan.ui.auth.PasswordSetupViewModel
import com.fc4rica.bonbaan.ui.auth.PersonalInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::RegisterRepositoryImpl) bind RegisterRepository::class
    viewModelOf(::InputEmailViewModel)
    viewModelOf(::PersonalInfoViewModel)
    viewModelOf(::PasswordSetupViewModel)
    viewModelOf(::EmailVerificationViewModel)
}

fun initKoin() {
    startKoin{
        modules(appModule)
    }
}

val previewModule = module {
    singleOf(::RegisterRepositoryImpl) bind RegisterRepository::class
    viewModelOf(::InputEmailViewModel)
    viewModelOf(::PersonalInfoViewModel)
    viewModelOf(::PasswordSetupViewModel)
    viewModelOf(::EmailVerificationViewModel)
}