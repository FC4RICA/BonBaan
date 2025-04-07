package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.Status
import com.google.gson.annotations.SerializedName

data class StatusResponse(
    @SerializedName("ID")
    val id: String,
    val name: String,
)

fun StatusResponse.toStatus(): Status {
    return Status(
        id = id,
        name = name
    )
}
