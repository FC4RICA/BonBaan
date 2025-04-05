package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.OrderType
import com.google.gson.annotations.SerializedName

data class OrderTypeResponse(
    @SerializedName("ID")
    val id: String,
    val name: String,
)

fun OrderTypeResponse.toOrderType(): OrderType {
    return OrderType(
        id = id,
        name = name
    )
}