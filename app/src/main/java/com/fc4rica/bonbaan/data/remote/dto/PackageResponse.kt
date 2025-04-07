package com.fc4rica.bonbaan.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.fc4rica.bonbaan.domain.model.Package

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

fun PackageResponse.toPackage(): Package {
    return Package(
        id = id,
        name = name,
        items = item,
        price = price,
        description = description,
        orderType = orderType.toOrderType()
    )
}