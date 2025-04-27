package com.fc4rica.bonbaan.domain.model

data class Status(
    val id: String,
    val name: String
)

enum class OrderStatus(val displayName: String, val engName: String) {
    Pending("รอรับออเดอร์", "pending"),
    Unpaid("รอการชำระเงิน", "unpaid"),
    Processing("กำลังดำเนินการ", "in progress"),
    Confirm("รอการอนุมัติ", "to be confirmed"),
    Approve("อนุมัติแล้ว", "approved"),
    Review("รอการรีวิว", "to be reviewed"),
    Completed("เสร็จสิ้น", "completed"),
    Refund("คืนเงิน", "refunded"),
    Cancel("ยกเลิก", "cancelled")
}