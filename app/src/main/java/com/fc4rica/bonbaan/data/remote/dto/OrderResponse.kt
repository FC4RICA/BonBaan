package com.fc4rica.bonbaan.data.remote.dto

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("ID")
    val id: String,
    val price: Double,
    val items: List<String>,
    @SerializedName("package")
    val packageItem: PackageResponse?,
    @SerializedName("CreatedAt")
    val createdAt: String,
    val transaction: TransactionResponse?,
    @SerializedName("CancellationReason")
    val cancellationReason: String?,
    val status: StatusResponse,
    val attachments: List<AttachmentResponse> = emptyList(),
    val service: ServiceResponse?,
)

data class OrdersResponse(
    val orders: List<OrderResponse>,
    val pagination: Pagination
)