package com.example.hiltapp.repository

import com.example.hiltapp.api.ApiService
import com.example.hiltapp.model.ResponseMeal
import retrofit2.Response
import javax.inject.Inject


class RepositoryMain @Inject constructor(private val apiService: ApiService) {
    suspend fun getMeals(): Response<ResponseMeal> {
        return apiService.getMeals()
    }
}
