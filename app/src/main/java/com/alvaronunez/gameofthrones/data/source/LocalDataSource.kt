package com.alvaronunez.gameofthrones.data.source

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CategoryDTO

interface LocalDataSource {
    suspend fun isCategoriesEmpty(): Result<Boolean>
    suspend fun getCategories(): Result<List<CategoryDTO>>
    suspend fun saveCategories(categories: List<CategoryDTO>): Result<Boolean>
}