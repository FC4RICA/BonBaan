package com.fc4rica.bonbaan.data.local

import androidx.datastore.core.Serializer
import com.fc4rica.bonbaan.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import java.util.Base64

@Serializable
data class UserPreferences(
    val token: String? = null,
    val id: String? = null,
    val email: String? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val username: String? = null,
    val phone: String? = null
)

object UserPreferencesSerializer: Serializer<UserPreferences> {
    override val defaultValue: UserPreferences
        get() = UserPreferences()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        val encryptedBytes = withContext(Dispatchers.IO) {
            input.use { it.readBytes() }
        }
        val encryptedBytesDecoded = Base64.getDecoder().decode(encryptedBytes)
        val decryptedBytes = Crypto.decrypt(encryptedBytesDecoded)
        val decodedJsonString = decryptedBytes.decodeToString()
        return Json.decodeFromString(decodedJsonString)
    }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        val json = Json.encodeToString(t)
        val bytes = json.toByteArray()
        val encryptedBytes = Crypto.encrypt(bytes)
        val encryptedBytesBase64 = Base64.getEncoder().encode(encryptedBytes)
        withContext(Dispatchers.IO) {
            output.use {
                it.write(encryptedBytesBase64)
            }
        }
    }
}

fun UserPreferences.toUser(): User? {
    return if (
        id != null && email != null && firstname != null &&
        lastname != null && username != null && phone != null
    ) {
        User(
            id = id,
            email = email,
            firstname = firstname,
            lastname = lastname,
            username = username,
            phone = phone
        )
    } else null
}