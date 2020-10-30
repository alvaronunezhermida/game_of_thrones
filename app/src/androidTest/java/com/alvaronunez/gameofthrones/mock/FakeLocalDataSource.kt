package com.alvaronunez.gameofthrones.mock

import android.content.Context
import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.data.source.LocalDataSource
import com.alvaronunez.gameofthrones.utils.MockUtils
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class FakeLocalDataSource(private val moshi: Moshi, private val context: Context) : LocalDataSource {
    override suspend fun isCategoriesEmpty(): Boolean = false

    override suspend fun getCategories(): Result<List<CategoryDTO>> =
        moshi.adapter<List<CategoryDTO>>(Types.newParameterizedType(List::class.java, CategoryDTO::class.java)).fromJson(
            MockUtils.readJsonFile(context, "categories.json"))?.let { dto ->
            Result.Response(dto)
        } ?: Result.Error()

    override suspend fun saveCategories(categories: List<CategoryDTO>): Result<Boolean> =
        Result.Response(true)

}