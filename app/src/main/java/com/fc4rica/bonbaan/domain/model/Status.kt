package com.fc4rica.bonbaan.domain.model

import androidx.compose.ui.graphics.Color

data class Status(
    val id: String,
    val name: String
)

enum class OrderStatus(val displayName: String, val engName: String, val color: Color) {
    Pending("รอรับออเดอร์", "pending", Color(0xFFF9B533)),
    Unpaid("รอการชำระเงิน", "unpaid", Color(0xFFF9B533)),
    Processing("กำลังดำเนินการ", "in progress", Color(0xFF46923C)),
    Confirm("รอการอนุมัติ", "to be confirmed", Color(0xFFF9B533)),
    Approve("อนุมัติแล้ว", "approved", Color(0xFF46923C)),
    Review("รอการรีวิว", "to be reviewed", Color(0xFF46923C)),
    Completed("เสร็จสิ้น", "completed", Color(0xFF46923C)),
    Refund("คืนเงิน", "refunded", Color(0xFFCA3435)),
    Cancel("ยกเลิก", "cancelled", Color(0xFFCA3435))
}

fun Status.toOrderStatus(): OrderStatus? {
    return OrderStatus.entries.find { it.engName.equals(this.name, ignoreCase = true) }
}