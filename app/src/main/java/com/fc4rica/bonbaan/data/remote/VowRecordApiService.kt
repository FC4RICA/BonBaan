package com.fc4rica.bonbaan.data.remote

import com.fc4rica.bonbaan.data.remote.dto.ApiResponse
import com.fc4rica.bonbaan.data.remote.dto.VowRecordResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface VowRecordApiService {
    @GET("users/{id}/vow-records")
    suspend fun getVowRecords(@Path("id") userId: String): ApiResponse<List<VowRecordResponse>>

    @GET("vow-records/{id}")
    suspend fun getVowRecord(@Path("id") vowRecordId: String): ApiResponse<VowRecordResponse>
}