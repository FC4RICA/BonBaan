package com.fc4rica.bonbaan.data.repository

import com.fc4rica.bonbaan.data.remote.ServiceApiService
import com.fc4rica.bonbaan.data.remote.dto.toPackage
import com.fc4rica.bonbaan.domain.model.Package
import com.fc4rica.bonbaan.domain.repository.PackageRepository

class PackageRepositoryImpl(
    private val serviceApiService: ServiceApiService
) : PackageRepository {
    override suspend fun getPackage(id: String): Result<Package> {
        return try {
            val response = serviceApiService.getPackage(id)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.toPackage())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPackagesByService(serviceId: String): Result<List<Package>> {
        return try {
            val response = serviceApiService.getPackages(serviceId)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.map { it.toPackage() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}