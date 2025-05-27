package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.Attachment

data class AttachmentResponse(
    val id: String?,
    val url: String
)

fun AttachmentResponse.toAttachment(): Attachment {
    return Attachment(
        id = id,
        url = url
    )
}
