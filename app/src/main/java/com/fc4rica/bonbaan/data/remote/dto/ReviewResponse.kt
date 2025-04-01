package com.fc4rica.bonbaan.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    // TODO: recheck for fields naming
    @SerializedName("ID")
    val id: String,
    val rating: Double,
    val detail: String,
    @SerializedName("User")
    val user: UserResponse,
    @SerializedName("Service")
    val service: ServiceResponse?,
    @SerializedName("CreatedAt")
    val createdAt: String
)
