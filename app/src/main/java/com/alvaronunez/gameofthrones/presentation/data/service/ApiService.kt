package com.alvaronunez.gameofthrones.presentation.data.service


import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService{
    @GET("categories")
    fun getCategories(): Deferred<Result<List<CategoryDTO>>>
}