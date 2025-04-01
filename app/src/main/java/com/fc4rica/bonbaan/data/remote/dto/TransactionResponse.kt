package com.fc4rica.bonbaan.data.remote.dto

import com.fc4rica.bonbaan.domain.model.Charge
import com.fc4rica.bonbaan.domain.model.Transaction
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TransactionResponse(
    @SerializedName("ID")
    val id: String,
    val price: Double,
    @SerializedName("chargeID")
    val chargeId: String,
    val charge: ChargeResponse?,
    @SerializedName("CreatedAt")
    val createdAt: String
)

data class ChargeResponse(
    val id: String,
    val status: String,
    val amount: Double,
    val currency: String,
    @SerializedName("captured_amount")
    val capturedAmount: Double,
    @SerializedName("authorized_amount")
    val authorizedAmount: Double,
    val paid: Boolean,
    @SerializedName("refunded")
    val refundedAmount: Double,
    @SerializedName("failure_message")
    val failureMessage: String?,
    @SerializedName("created")
    val createdAt: String
)

fun TransactionResponse.toTransaction(): Transaction {
    val formatter = DateTimeFormatter.ISO_DATE_TIME

    return Transaction(
        id = this.id,
        price = this.price,
        chargeId = this.chargeId,
        charge = this.charge?.let {
            Charge(
                id = it.id,
                status = it.status,
                amount = it.amount,
                currency = it.currency,
                capturedAmount = it.capturedAmount,
                authorizedAmount = it.authorizedAmount,
                paid = it.paid,
                refundedAmount = it.refundedAmount,
                failureMessage = it.failureMessage,
                createdAt = LocalDateTime.parse(it.createdAt, formatter)
            )
        },
        createdAt = LocalDateTime.parse(this.createdAt, formatter)
    )
}