package com.fc4rica.bonbaan.data.remote

import com.fc4rica.bonbaan.data.remote.dto.ApiResponse
import com.fc4rica.bonbaan.data.remote.dto.CategoryResponse
import com.fc4rica.bonbaan.data.remote.dto.PackageResponse
import com.fc4rica.bonbaan.data.remote.dto.ReviewResponse
import com.fc4rica.bonbaan.data.remote.dto.ServiceResponse
import com.fc4rica.bonbaan.data.remote.dto.ServicesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApiService {
    @GET("services")
    suspend fun getServices(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("search") search: String? = null,
        @Query("orderBy") orderBy: String? = null,
        @Query("orderDirection") orderDirection: String? = null
    ): ApiResponse<ServicesResponse>

    @GET("services/recommend")
    suspend fun getRecommendedServices(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10
    ): ApiResponse<ServicesResponse>

    @GET("services/popular")
    suspend fun getPopularServices(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10
    ): ApiResponse<ServicesResponse>

    @GET("services/{id}")
    suspend fun getService(@Path("id") serviceId: String): ApiResponse<ServiceResponse>

    // Categories
    @GET("categories/{id}/services")
    suspend fun getServicesByCategory(@Path("id") categoryId: String): ApiResponse<List<ServiceResponse>>

    @GET("categories")
    suspend fun getCategories(): ApiResponse<List<CategoryResponse>>

    @GET("categories/{id}")
    suspend fun getCategory(@Path("id") categoryId: String): ApiResponse<CategoryResponse>

    // Packages
    @GET("services/{id}/packages")
    suspend fun getPackages(@Path("id") userId: String): ApiResponse<List<PackageResponse>>

    @GET("packages/{id}")
    suspend fun getPackage(@Path("id") packageId: String): ApiResponse<PackageResponse>

    // Review
    @GET("services/{id}/reviews")
    suspend fun getServiceReviews(@Path("id") serviceId: String): ApiResponse<List<ReviewResponse>>
}