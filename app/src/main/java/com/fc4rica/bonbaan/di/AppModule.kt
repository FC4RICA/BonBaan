package com.fc4rica.bonbaan.di

import com.fc4rica.bonbaan.data.repository.AuthRepositoryImpl
import com.fc4rica.bonbaan.data.repository.CategoryRepositoryImpl
import com.fc4rica.bonbaan.data.repository.RegisterRepositoryImpl
import com.fc4rica.bonbaan.data.repository.InterestRepositoryImpl
import com.fc4rica.bonbaan.data.repository.NotificationRepositoryImpl
import com.fc4rica.bonbaan.data.repository.OrderRepositoryImpl
import com.fc4rica.bonbaan.data.repository.ReviewRepositoryImpl
import com.fc4rica.bonbaan.data.repository.ServiceRepositoryImpl
import com.fc4rica.bonbaan.domain.repository.AuthRepository
import com.fc4rica.bonbaan.domain.repository.CategoryRepository
import com.fc4rica.bonbaan.domain.repository.InterestRepository
import com.fc4rica.bonbaan.domain.repository.NotificationRepository
import com.fc4rica.bonbaan.domain.repository.OrderRepository
import com.fc4rica.bonbaan.domain.repository.RegisterRepository
import com.fc4rica.bonbaan.domain.repository.ReviewRepository
import com.fc4rica.bonbaan.domain.repository.ServiceRepository
import com.fc4rica.bonbaan.ui.MainViewModel
import com.fc4rica.bonbaan.ui.auth.LoginViewModel
import com.fc4rica.bonbaan.ui.auth.register.EmailVerificationViewModel
import com.fc4rica.bonbaan.ui.auth.register.InputEmailViewModel
import com.fc4rica.bonbaan.ui.auth.register.PasswordSetupViewModel
import com.fc4rica.bonbaan.ui.auth.register.PersonalInfoViewModel
import com.fc4rica.bonbaan.ui.home.feed.FeedViewModel
import com.fc4rica.bonbaan.ui.home.feed.SearchViewModel
import com.fc4rica.bonbaan.ui.home.notification.NotificationViewModel
import com.fc4rica.bonbaan.ui.home.profile.OrderStatusDetailViewModel
import com.fc4rica.bonbaan.ui.home.profile.OrdersStatusViewModel
import com.fc4rica.bonbaan.ui.home.profile.PreviousReviewsViewModel
import com.fc4rica.bonbaan.ui.home.profile.ProfileViewModel
import com.fc4rica.bonbaan.ui.home.vow_record.VowRecordDetailViewModel
import com.fc4rica.bonbaan.ui.home.vow_record.VowRecordViewModel
import com.fc4rica.bonbaan.ui.onboarding.InterestViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::RegisterRepositoryImpl) bind RegisterRepository::class
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    singleOf(::InterestRepositoryImpl) bind InterestRepository::class
    singleOf(::CategoryRepositoryImpl) bind CategoryRepository::class
    singleOf(::NotificationRepositoryImpl) bind NotificationRepository::class
    singleOf(::OrderRepositoryImpl) bind OrderRepository::class
    singleOf(::ReviewRepositoryImpl) bind ReviewRepository::class
    singleOf(::ServiceRepositoryImpl) bind ServiceRepository::class

    viewModelOf(::MainViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::InputEmailViewModel)
    viewModelOf(::PersonalInfoViewModel)
    viewModelOf(::PasswordSetupViewModel)
    viewModelOf(::EmailVerificationViewModel)
    viewModelOf(::InterestViewModel)
    viewModelOf(::VowRecordViewModel)
    viewModelOf(::VowRecordDetailViewModel)
    viewModelOf(::NotificationViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::OrdersStatusViewModel)
    viewModelOf(::OrderStatusDetailViewModel)
    viewModelOf(::PreviousReviewsViewModel)
    viewModelOf(::FeedViewModel)
    viewModelOf(::SearchViewModel)
}