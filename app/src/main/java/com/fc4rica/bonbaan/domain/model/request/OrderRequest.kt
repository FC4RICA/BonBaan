package com.fc4rica.bonbaan.domain.model.request

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class VowOrderRequest(
    val price: Double,
    val items: List<String>,
    val packageID: String?,
    val userID: String,
    val deadline: LocalDateTime,
    val note: String,
    val vow: String,
    val serviceID: String,
    @SerializedName("order_type_ID")
    val orderTypeID: String,
)

data class FulfillOrderRequest(
    val price: Double,
    val items: List<String>,
    val packageID: String?,
    val userID: String,
    val serviceID: String,
    @SerializedName("order_type_ID")
    val orderTypeID: String,
    @SerializedName("vow_record_id")
    val vowRecordID: String,
)