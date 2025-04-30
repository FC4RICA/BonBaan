package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.Service
import com.google.gson.annotations.SerializedName

data class ServiceResponse(
    @SerializedName("ID", alternate = ["id"])
    val id: String,
    val name: String,
    val description: String,
    val rate: Double,
    val address: String,
    val categories: List<CategoryResponse> = emptyList(),
    val packages: List<PackageResponse> = emptyList(),
    val attachments: List<AttachmentResponse> = emptyList(),
)

data class ServicesResponse(
    val services: List<ServiceResponse>,
    val pagination: Pagination
)

fun ServiceResponse.toService(
    mapCategoryId: Boolean = true,
    mapPackage: Boolean = true,
): Service {
    return Service(
        id = id,
        name = name,
        description = description,
        rate = rate,
        address = address,
        categories = categories.map { it.toCategory(mapCategoryId) },
        packages = if (mapPackage) packages.map { it.toPackage() } else emptyList(),
        attachments = attachments.map { it.toAttachment() },
    )
}