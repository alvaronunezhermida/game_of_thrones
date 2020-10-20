package com.alvaronunez.gameofthrones.presentation.data.database.daos

import androidx.room.*
import com.alvaronunez.gameofthrones.presentation.data.database.entities.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM CategoryEntity")
    fun getAll(): List<CategoryEntity>

    @Query("SELECT COUNT(id) FROM CategoryEntity")
    fun categoriesCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategories(categories: List<CategoryEntity>)
}