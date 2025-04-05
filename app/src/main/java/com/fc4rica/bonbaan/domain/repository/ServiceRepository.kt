package com.fc4rica.bonbaan.domain.repository

import android.graphics.pdf.PdfDocument.Page
import com.fc4rica.bonbaan.domain.model.Service

interface ServiceRepository {
    suspend fun getServices(): Result<List<Service>>
    suspend fun getService(id: String): Result<Service>
    suspend fun searchServices(query: String): Result<List<Service>>
    suspend fun getRecommendedServices(page: Int, pageSize: Int = 10): Result<List<Service>>
    suspend fun getPopularServices(page: Int, pageSize: Int = 10): Result<List<Service>>
    suspend fun getServicesByCategory(categoryId: String): Result<List<Service>>
}