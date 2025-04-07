package com.fc4rica.bonbaan.data.remote

import com.fc4rica.bonbaan.data.remote.dto.ApiResponse
import com.fc4rica.bonbaan.data.remote.dto.ReviewResponse
import com.fc4rica.bonbaan.domain.model.request.ReviewRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewApiService {
    @GET("users/{id}/reviews")
    suspend fun getMyReviews(@Path("id") userId: String): ApiResponse<List<ReviewResponse>>
    @GET("reviews/{id}")
    suspend fun getReview(@Path("id") reviewId: String): ApiResponse<ReviewResponse>
    @POST("reviews")
    suspend fun createReview(@Body review: ReviewRequest): ApiResponse<ReviewResponse>

}