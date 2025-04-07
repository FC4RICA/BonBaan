package com.fc4rica.bonbaan.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.fc4rica.bonbaan.domain.model.User

class SecurePreferences(context: Context) {
    private val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secret_pref",
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveUserData(user: User) {
        sharedPreferences.edit().apply {
            putString("id", user.id)
            putString("email", user.email)
            putString("firstname", user.firstname)
            putString("lastname", user.lastname)
            putString("username", user.username)
            putString("phone", user.phone)
        }.apply()
    }

    fun getUserData(): User? {
        return User(
            id = sharedPreferences.getString("id", null) ?: return null,
            email = sharedPreferences.getString("email", null) ?: return null,
            firstname = sharedPreferences.getString("firstname", null) ?: return null,
            lastname = sharedPreferences.getString("lastname", null) ?: return null,
            username = sharedPreferences.getString("username", null) ?: return null,
            phone = sharedPreferences.getString("phone", null) ?: return null
        )
    }

    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }
}