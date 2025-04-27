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
    val `object`: String,
    val id: String,
    val livemode: Boolean,
    val location: String?,
    val created: String,
    val status: String,
    val amount: Double,
    @SerializedName("authorization_type")
    val authorizationType: String,
    @SerializedName("authorized_amount")
    val authorizedAmount: Double,
    @SerializedName("captured_amount")
    val capturedAmount: Double,
    val currency: String,
    val description: String?,
    val capture: Boolean,
    val authorized: Boolean,
    val reversed: Boolean,
    val paid: Boolean,
    val transaction: String,
    val card: String?,
    @SerializedName("refunded")
    val refundedAmount: Double,
    val refunds: String?,
    @SerializedName("failure_code")
    val failureCode: String?,
    @SerializedName("failure_message")
    val failureMessage: String?,
    val customer: String,
    val ip: String?,
    val dispute: String?,
    @SerializedName("return_uri")
    val returnUri: String,
    @SerializedName("authorize_uri")
    val authorizeUri: String,
    @SerializedName("source_of_fund")
    val sourceOfFund: String,
    val offsite: String,
    val source: String?,
    val metadata: String?,
    @SerializedName("expires_at")
    val expiresAt: String
)

fun TransactionResponse.toTransaction(): Transaction {
    val formatter = DateTimeFormatter.ISO_DATE_TIME

    return Transaction(
        id = this.id,
        price = this.price,
        chargeId = this.chargeId,
        charge = this.charge?.let {
            Charge(
                `object` = it.`object`,
                id = it.id,
                livemode = it.livemode,
                location = it.location,
                createdAt = LocalDateTime.parse(it.created, formatter),
                status = it.status,
                amount = it.amount,
                authorizationType = it.authorizationType,
                authorizedAmount = it.authorizedAmount,
                capturedAmount = it.capturedAmount,
                currency = it.currency,
                description = it.description,
                capture = it.capture,
                authorized = it.authorized,
                reversed = it.reversed,
                paid = it.paid,
                transaction = it.transaction,
                card = it.card,
                refundedAmount = it.refundedAmount,
                refunds = it.refunds,
                failureCode = it.failureCode,
                failureMessage = it.failureMessage,
                customer = it.customer,
                ip = it.ip,
                dispute = it.dispute,
                returnUri = it.returnUri,
                authorizeUri = it.authorizeUri,
                sourceOfFund = it.sourceOfFund,
                offsite = it.offsite,
                source = it.source,
                metadata = it.metadata,
                expiresAt = LocalDateTime.parse(it.expiresAt, formatter)
            )
        },
        createdAt = LocalDateTime.parse(this.createdAt, formatter)
    )
}