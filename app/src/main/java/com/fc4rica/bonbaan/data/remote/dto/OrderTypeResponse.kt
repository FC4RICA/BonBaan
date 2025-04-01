package com.fc4rica.bonbaan.data.remote.dto

import com.google.gson.annotations.SerializedName

data class OrderTypeResponse(
    @SerializedName("ID")
    val id: String,
    val name: String,
)
