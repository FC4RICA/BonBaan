package com.fc4rica.bonbaan.di

import com.fc4rica.bonbaan.data.repository.UserRepositoryImpl
import com.fc4rica.bonbaan.domain.repository.UserRepository
import com.fc4rica.bonbaan.domain.usecase.user.UserUseCase
import com.fc4rica.bonbaan.ui.auth.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::UserRepositoryImpl) bind UserRepository::class
    factoryOf(::UserUseCase)
    viewModelOf(::RegisterViewModel)
}

fun initKoin() {
    startKoin{
        modules(appModule)
    }
}