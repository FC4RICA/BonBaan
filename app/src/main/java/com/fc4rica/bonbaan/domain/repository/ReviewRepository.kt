package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.Review

interface ReviewRepository {
    suspend fun getReview(id: String): Result<Review>
    suspend fun getReviews(): Result<List<Review>>
    suspend fun createReview(review: Review): Result<Review>
    suspend fun getReviewsByService(id: String): Result<List<Review>>
}