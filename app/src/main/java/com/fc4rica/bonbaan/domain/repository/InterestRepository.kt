package com.fc4rica.bonbaan.domain.repository

import com.fc4rica.bonbaan.domain.model.Category
import com.fc4rica.bonbaan.domain.model.request.InterestRequest

interface InterestRepository {
    suspend fun getInterests(): Result<List<Category>>
    suspend fun addInterest(interests: InterestRequest): Result<Unit>
}