package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("ID")
    val id: String,
    val email: String,
    val firstname: String,
    val lastname: String,
    val username: String,
    val phone: String,
)

fun UserResponse.toUser(): User {
    return User(
        id = this.id,
        email = this.email,
        firstname = this.firstname,
        lastname = this.lastname,
        username = this.username,
        phone = this.phone
    )
}