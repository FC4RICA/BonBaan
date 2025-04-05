package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.Review
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

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

fun ReviewResponse.toReview(): Review {
    return Review(
        id = id,
        rating = rating,
        detail = detail,
        user = user.toUser(),
        service = service?.toService(),
        createdAt = LocalDateTime.parse(createdAt)
    )
}