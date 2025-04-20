package com.fc4rica.bonbaan.data.repository

import androidx.datastore.core.DataStore
import com.fc4rica.bonbaan.data.local.UserPreferences
import com.fc4rica.bonbaan.data.remote.ReviewApiService
import com.fc4rica.bonbaan.data.remote.ServiceApiService
import com.fc4rica.bonbaan.data.remote.dto.toReview
import com.fc4rica.bonbaan.domain.model.Review
import com.fc4rica.bonbaan.domain.model.request.ReviewRequest
import com.fc4rica.bonbaan.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.first

class ReviewRepositoryImpl(
    private val reviewApiService: ReviewApiService,
    private val serviceApiService: ServiceApiService,
    private val userPreferences: DataStore<UserPreferences>,
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
            val userId = userPreferences.data.first().id
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

    override suspend fun createReview(review: ReviewRequest): Result<Review> {
        return try {
            val response = reviewApiService.createReview(review)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }
            Result.success(response.data.toReview())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getReviewsByService(id: String): Result<List<Review>> {
        return try {
            val response = serviceApiService.getServiceReviews(id)
            if (response.error != null || response.data == null) {
                return Result.failure(Exception(response.error))
            }

            Result.success(response.data.map { it.toReview() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}