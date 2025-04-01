package com.fc4rica.bonbaan.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PackageResponse(
    @SerializedName("ID")
    val id: String,
    val name: String,
    val item: List<String>,
    val price: Double,
    val description: String,
    @SerializedName("OrderType")
    val orderType: OrderTypeResponse,
)
