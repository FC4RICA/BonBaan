package com.fc4rica.bonbaan.di

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.fc4rica.bonbaan.BuildConfig
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.data.local.UserPreferencesSerializer
import com.fc4rica.bonbaan.data.remote.UserApiService
import okhttp3.OkHttpClient
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

fun provideUserPreferences(context: Context): DataStore<UserPreferences> {
    return context.dataStore
}

fun provideUserService(retrofit: Retrofit): UserApiService =
    retrofit.create(UserApiService::class.java)

val networkModule = module {
    singleOf(::provideUserPreferences)
    singleOf(::AuthInterceptor)
    singleOf(::provideHttpClient)
    singleOf(::provideRetrofit)
    singleOf(::provideUserService)
}