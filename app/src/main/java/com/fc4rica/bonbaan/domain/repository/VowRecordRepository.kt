package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.VowRecord

interface VowRecordRepository {
    suspend fun getVowRecords(): Result<List<VowRecord>>
    suspend fun getVowRecord(id: String): Result<VowRecord>
}