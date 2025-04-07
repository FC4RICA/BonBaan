package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.VowRecord
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

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

fun VowRecordResponse.toVowRecord(): VowRecord {
    return VowRecord(
        id = id,
        vow = vow,
        deadline = LocalDateTime.parse(deadline),
        note = note,
        service = service?.toService(),
        vowOrder = vowOrder?.toOrder(),
        fulfillOrder = fulfillOrder?.toOrder(),
        createdAt = LocalDateTime.parse(createdAt)
    )
}