package com.fc4rica.bonbaan.utils

import android.util.Patterns
import androidx.core.text.isDigitsOnly

fun String.isValidEmail(): Boolean {
    return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidName(): Pair<Boolean, String?> {
    return when {
        this.isBlank() -> false to "กรุณากรอกชื่อและนามสกุล"
        !this.contains(" ") -> false to "กรุณาเว้นวรรคระหว่างชื่อและนามสกุล"
        else -> true to null
    }
}

fun String.isValidPhone(): Pair<Boolean, String?> {
    return when {
        this.isBlank() -> false to "กรุณากรอกหมายเลขโทรศัพท์"
        !this.isDigitsOnly() -> false to "ห้ามใช้ตัวอักษรในหมายเลขโทรศัพท์"
        this.length != 10 -> false to "หมายเลขโทรศัพท์ต้องมี 10 หลัก"
        else -> true to null
    }
}

fun String.isValidUsername(): Pair<Boolean, String?> {
    return when{
        this.isBlank() -> false to "กรุณากรอกชื่อผู้ใช้"
        this.length > 32 -> false to "ชื่อผู้ใช้ของคุณยาวเกินไป"
        else -> true to null
    }
}