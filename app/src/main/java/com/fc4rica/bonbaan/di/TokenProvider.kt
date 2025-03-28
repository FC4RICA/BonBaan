package com.fc4rica.bonbaan.di

import android.content.Context
import android.content.SharedPreferences

class TokenProvider(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun getToken(): String? = sharedPreferences.getString("token", null)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun clearToken() {
        sharedPreferences.edit().remove("token").apply()
    }
}