package com.example.hiltapp.api

import com.example.hiltapp.model.ResponseMeal
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("filter.php?c=Beef")
    suspend fun getMeals(): Response<ResponseMeal>
}