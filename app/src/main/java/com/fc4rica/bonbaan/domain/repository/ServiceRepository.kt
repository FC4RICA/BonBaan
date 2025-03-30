package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.Service

interface ServiceRepository {
    suspend fun getServices(): Result<List<Service>>
    suspend fun getService(id: String): Result<Service>
    suspend fun searchServices(query: String): Result<List<Service>>
    suspend fun getRecommendedServices(): Result<List<Service>>
    suspend fun getPopularServices(): Result<List<Service>>
    suspend fun getTopRatedServices(): Result<List<Service>>
    suspend fun getServicesByCategory(categoryId: String): Result<List<Service>>
}