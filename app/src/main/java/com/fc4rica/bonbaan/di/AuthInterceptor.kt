package com.fc4rica.bonbaan.di

import android.util.Log
import androidx.datastore.core.DataStore
import com.fc4rica.bonbaan.data.local.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val userPreferences: DataStore<UserPreferences>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val token = runBlocking {
            userPreferences.data.first().token
        }
        Log.d("AuthInterceptor", "intercept: $token")

        val newRequest =
            original.newBuilder().apply {
                if (!token.isNullOrEmpty()) {
                    addHeader("Authorization", "Bearer $token")
                }
            }.build()

        return chain.proceed(newRequest)
    }
}