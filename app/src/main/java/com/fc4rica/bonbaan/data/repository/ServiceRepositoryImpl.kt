package com.fc4rica.bonbaan.data.repository

import com.fc4rica.bonbaan.data.remote.ServiceApiService
import com.fc4rica.bonbaan.data.remote.dto.toService
import com.fc4rica.bonbaan.domain.model.Service
import com.fc4rica.bonbaan.domain.model.request.PaginationRequest
import com.fc4rica.bonbaan.domain.repository.ServiceRepository

class ServiceRepositoryImpl(
    private val serviceApiService: ServiceApiService
) : ServiceRepository {
    override suspend fun getServices(pagination: PaginationRequest): Result<List<Service>> {
        return try {
            val response = serviceApiService.getServices(
                page = pagination.page,
                pageSize = pagination.pageSize,
                search = pagination.search,
                orderBy = pagination.orderBy,
            )
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.services.map { it.toService(false, false) })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getService(id: String): Result<Service> {
        return try {
            val response = serviceApiService.getService(id)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.toService())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRecommendedServices(pagination: PaginationRequest): Result<List<Service>> {
        return try {
            val response = serviceApiService.getRecommendedServices(
                page = pagination.page,
                pageSize = pagination.pageSize,
                search = pagination.search,
                orderBy = pagination.orderBy,
            )
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }
            val mappedService = response.data.services.map { it.toService(false, false) }
            Result.success(mappedService)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBestSellerServices(pagination: PaginationRequest): Result<List<Service>> {
        return try {
            val response = serviceApiService.getBestSellerServices(
                page = pagination.page,
                pageSize = pagination.pageSize,
                search = pagination.search,
                orderBy = pagination.orderBy,
            )
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }
            Result.success(response.data.services.map { it.toService(false, false) })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getServicesByCategory(id: String): Result<List<Service>> {
        return try {
            val categoryResponse = serviceApiService.getCategory(id)
            if (categoryResponse.error != null || categoryResponse.data == null) {
                return Result.failure(Exception(categoryResponse.error))
            }
            val categoryName = categoryResponse.data.name

            val serviceResponse = serviceApiService.getServices(
                page = 1,
                pageSize = 999,
            )
            if (serviceResponse.error != null || serviceResponse.data == null) {
                return Result.failure(Exception(serviceResponse.error))
            }

            Result.success(serviceResponse.data.services.filter { it.categories.first().name == categoryName }
                .map { it.toService(false, false) })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}