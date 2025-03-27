package com.fc4rica.bonbaan.di

import android.util.Log
import com.fc4rica.bonbaan.BuildConfig
import com.fc4rica.bonbaan.data.remote.UserApiService
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideHttpClient(): OkHttpClient {
    Log.d("provideHttpClient", "provideHttpClient: Init")
    return OkHttpClient
        .Builder()
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

fun provideUserService(retrofit: Retrofit): UserApiService =
    retrofit.create(UserApiService::class.java)

val networkModule = module {
    singleOf(::provideHttpClient)
    singleOf(::provideRetrofit)
    singleOf(::provideUserService)
}