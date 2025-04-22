package com.fc4rica.bonbaan.domain.model.request

import com.google.gson.annotations.SerializedName

sealed class OrderRequest {
    data class Vow(val request: VowOrderRequest) : OrderRequest()
    data class Fulfill(val request: FulfillOrderRequest) : OrderRequest()
}

data class VowOrderRequest(
    val deadline: String,
    val note: String,
    val vow: String,
    val price: Double?,
    val items: List<String>,
    @SerializedName("packageID")
    val packageId: String = "",
    @SerializedName("serviceID")
    val serviceId: String,
    @SerializedName("order_type_ID")
    val orderTypeID: String,
    @SerializedName("vow_record_id")
    val vowRecordID: String = ""
)

data class FulfillOrderRequest(
    val price: Double?,
    val items: List<String> = emptyList(),
    @SerializedName("packageID")
    val packageId: String = "",
    @SerializedName("serviceID")
    val serviceId: String,
    @SerializedName("order_type_ID")
    val orderTypeID: String,
    @SerializedName("vow_record_id")
    val vowRecordID: String,
)