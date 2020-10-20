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

    override suspend fun isCategoriesEmpty(): Result<Boolean> =
        withContext(Dispatchers.IO) { Result.Response(categoryDao.categoriesCount() <= 0) }

    override suspend fun saveCategories(categories: List<CategoryDTO>) {
        withContext(Dispatchers.IO) { categoryDao.insertCategories(categories.map { it.toRoomCategoryEntity() }) }
    }

    override suspend fun getCategories(): Result<List<CategoryDTO>> = withContext(Dispatchers.IO) {
        Result.Response(categoryDao.getAll().map { it.toCategoryDTO() })
    }
}