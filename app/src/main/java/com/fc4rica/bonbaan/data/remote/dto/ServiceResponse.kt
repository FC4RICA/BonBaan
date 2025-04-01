package com.fc4rica.bonbaan.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ServiceResponse(
    // TODO: recheck for id naming
    @SerializedName("ID", alternate = ["id"])
    val id: String,
    val name: String,
    val description: String,
    val rate: Double,
    val categories: List<CategoryResponse> = emptyList(),
    val packages: List<PackageResponse> = emptyList(),
    val attachments: List<AttachmentResponse> = emptyList(),
    @SerializedName("CreatedAt")
    val createdAt: String
)

data class ServicesResponse(
    val services: List<ServiceResponse>,
    val pagination: Pagination
)
