package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.Package

interface PackageRepository {
    suspend fun getPackage(id: String): Result<Package>
    suspend fun getPackagesByService(serviceId: String): Result<List<Package>>
}