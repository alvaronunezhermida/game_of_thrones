package com.alvaronunez.gameofthrones.presentation.data.database

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.data.source.LocalDataSource
import com.alvaronunez.gameofthrones.presentation.data.toCategoryDTO
import com.alvaronunez.gameofthrones.presentation.data.toRoomCategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: AppDatabase) : LocalDataSource {

    private val categoryDao = db.categoryDao()

    override suspend fun isCategoriesEmpty(): Boolean =
        try {
            categoryDao.categoriesCount() <= 0
        }catch (e: Exception) {
            true
        }

    override suspend fun saveCategories(categories: List<CategoryDTO>): Result<Boolean> =
        try {
            categoryDao.insertCategories(categories.map { it.toRoomCategoryEntity() })
            Result.Response(true)
        }catch (e: Exception) {
            Result.Error(e.message)
        }

    override suspend fun getCategories(): Result<List<CategoryDTO>> =
        try {
            Result.Response(categoryDao.getAll().map { it.toCategoryDTO() })
        }catch (e: Exception) {
            Result.Error(e.message)
        }
}