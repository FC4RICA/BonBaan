package com.fc4rica.bonbaan.data.repository

import androidx.datastore.core.DataStore
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.data.remote.VowRecordApiService
import com.fc4rica.bonbaan.data.remote.dto.toVowRecord
import com.fc4rica.bonbaan.domain.model.VowRecord
import com.fc4rica.bonbaan.domain.repository.VowRecordRepository
import kotlinx.coroutines.flow.first

class VowRecordRepositoryImpl(
    private val vowRecordApiService: VowRecordApiService,
    private val userPreferences: DataStore<UserPreferences>,
) : VowRecordRepository {
    override suspend fun getVowRecords(): Result<List<VowRecord>> {
        return try {
            val userId = userPreferences.data.first().id
                ?: return Result.failure(Exception("User not logged in"))

            val response = vowRecordApiService.getVowRecords(userId)

            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.map { it.toVowRecord() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVowRecord(id: String): Result<VowRecord> {
        return try {
            val response = vowRecordApiService.getVowRecord(id)

            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.toVowRecord())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUnFulfilledVowRecordsByService(serviceId: String): Result<List<VowRecord>> {
        return try {
            val userId = userPreferences.data.first().id
                ?: return Result.failure(Exception("User not logged in"))

            val response = vowRecordApiService.getVowRecords(userId)

            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.filter { it.service?.id == serviceId && it.fulfillOrder == null }
                .map { it.toVowRecord() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}