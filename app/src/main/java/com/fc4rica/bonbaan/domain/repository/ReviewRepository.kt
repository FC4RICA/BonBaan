package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.Review
import com.fc4rica.bonbaan.domain.model.request.ReviewRequest

interface ReviewRepository {
    suspend fun getReview(id: String): Result<Review>
    suspend fun getReviews(): Result<List<Review>>
    suspend fun createReview(review: ReviewRequest): Result<Review>
    suspend fun getReviewsByService(id: String): Result<List<Review>>
}