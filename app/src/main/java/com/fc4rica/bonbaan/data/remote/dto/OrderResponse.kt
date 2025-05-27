package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.Order
import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime
import java.time.ZoneId

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
    val attachments: List<AttachmentResponse>? = emptyList(),
    val service: ServiceResponse?,
)

data class OrdersResponse(
    val orders: List<OrderResponse>,
    val pagination: Pagination
)

fun OrderResponse.toOrder(
    mapPackage: Boolean = true,
    mapTransaction: Boolean = true,
    mapService: Boolean = true,
    mapAttachments: Boolean = true
): Order {
    return Order(
        id = id,
        price = price,
        items = items,
        packageItem = if (mapPackage) packageItem?.toPackage() else null,
        createdAt = OffsetDateTime.parse(createdAt).atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime(),
        transaction = if (mapTransaction) transaction?.toTransaction() else null,
        cancellationReason = cancellationReason,
        status = status.toStatus(),
        attachments = if (mapAttachments && attachments != null) attachments.map { it.toAttachment() } else emptyList(),
        service = if (mapService) service?.toService(mapCategoryId = false, mapPackage = false, mapCategories = false) else null
    )
}