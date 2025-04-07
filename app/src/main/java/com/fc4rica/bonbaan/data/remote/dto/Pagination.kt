package com.fc4rica.bonbaan.data.remote.dto

data class Pagination(
    val pageSize: Int,
    val currentPage: Int,
    val totalPages: Int,
    val totalRecords: Int
)
