package com.fc4rica.bonbaan.domain.model.request

data class PaginationRequest(
    val page: Int,
    val pageSize: Int = 10,
    val search: String? = null,
    val orderBy: String? = null,
    val orderDirection: String? = null
)