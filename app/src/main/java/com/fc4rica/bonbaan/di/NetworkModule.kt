package com.fc4rica.bonbaan.di

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.fc4rica.bonbaan.BuildConfig
import com.fc4rica.bonbaan.data.local.SecurePreferences
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.data.local.UserPreferencesSerializer
import com.fc4rica.bonbaan.data.remote.NotificationApiService
import com.fc4rica.bonbaan.data.remote.OrderApiService
import com.fc4rica.bonbaan.data.remote.ReviewApiService
import com.fc4rica.bonbaan.data.remote.ServiceApiService
import com.fc4rica.bonbaan.data.remote.UserApiService
import com.fc4rica.bonbaan.data.remote.VowRecordApiService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideHttpClient(
    authInterceptor: AuthInterceptor
): OkHttpClient {
    Log.d("provideHttpClient", "provideHttpClient: Init")
    return OkHttpClient
        .Builder()
        .addInterceptor(authInterceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}

fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit {
    val baseUrl = BuildConfig.BASE_URL
    Log.d("provideRetrofit", "provideRetrofit: $baseUrl")
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val Context.dataStore: DataStore<UserPreferences> by dataStore(
    fileName = "user_prefs.pb",
    serializer = UserPreferencesSerializer
)

fun provideUserService(retrofit: Retrofit): UserApiService =
    retrofit.create(UserApiService::class.java)
fun provideServiceApiService(retrofit: Retrofit): ServiceApiService =
    retrofit.create(ServiceApiService::class.java)
fun provideOrderApiService(retrofit: Retrofit): OrderApiService =
    retrofit.create(OrderApiService::class.java)
fun provideReviewApiService(retrofit: Retrofit): ReviewApiService =
    retrofit.create(ReviewApiService::class.java)
fun provideNotificationApiService(retrofit: Retrofit): NotificationApiService =
    retrofit.create(NotificationApiService::class.java)
fun provideVowRecordApiService(retrofit: Retrofit): VowRecordApiService =
    retrofit.create(VowRecordApiService::class.java)

val networkModule = module {
    singleOf(::SecurePreferences)
    single<DataStore<UserPreferences>> { androidContext().dataStore }

    singleOf(::AuthInterceptor)
    singleOf(::provideHttpClient)
    singleOf(::provideRetrofit)

    singleOf(::provideUserService)
    singleOf(::provideServiceApiService)
    singleOf(::provideOrderApiService)
    singleOf(::provideReviewApiService)
    singleOf(::provideNotificationApiService)
    singleOf(::provideVowRecordApiService)
}