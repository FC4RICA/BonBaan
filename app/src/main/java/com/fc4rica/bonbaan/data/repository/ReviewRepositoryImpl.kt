package com.fc4rica.bonbaan.data.repository

import com.fc4rica.bonbaan.data.local.SecurePreferences
import com.fc4rica.bonbaan.data.remote.ReviewApiService
import com.fc4rica.bonbaan.data.remote.dto.toReview
import com.fc4rica.bonbaan.domain.model.Review
import com.fc4rica.bonbaan.domain.repository.ReviewRepository

class ReviewRepositoryImpl(
    private val reviewApiService: ReviewApiService,
    private val securePreferences: SecurePreferences
) : ReviewRepository {
    override suspend fun getReview(id: String): Result<Review> {
        return try {
            val response = reviewApiService.getReview(id)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.toReview())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getReviews(): Result<List<Review>> {
        return try {
            val userId = securePreferences.getUserData()?.id
                ?: return Result.failure(Exception("User ID not found"))

            val response = reviewApiService.getMyReviews(userId)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.map { it.toReview() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createReview(review: Review): Result<Review> {
        TODO("Not yet implemented")
    }
}