package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.model.request.PaginationRequest

interface ServiceRepository {
    suspend fun getServices(pagination: PaginationRequest): Result<List<Service>>
    suspend fun getService(id: String): Result<Service>
    suspend fun getRecommendedServices(pagination: PaginationRequest): Result<List<Service>>
    suspend fun getBestSellerServices(pagination: PaginationRequest): Result<List<Service>>
    suspend fun getServicesByCategory(id: String): Result<List<Service>>
}