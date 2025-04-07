package com.fc4rica.bonbaan.data.remote.dto

import com.google.gson.annotations.SerializedName

data class VowRecordResponse(
    @SerializedName("ID")
    val id: String,
    val vow: String,
    val deadline: String,
    val note: String,
    val service: ServiceResponse?,
    val vowOrder: OrderResponse?,
    val fulfillOrder: OrderResponse?,
    @SerializedName("CreatedAt")
    val createdAt: String
)
