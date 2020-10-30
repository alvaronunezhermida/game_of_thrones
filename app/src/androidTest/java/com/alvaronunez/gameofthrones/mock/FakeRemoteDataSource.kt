package com.alvaronunez.gameofthrones.mock

import android.content.Context
import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.data.models.CharDTO
import com.alvaronunez.gameofthrones.data.models.HouseDTO
import com.alvaronunez.gameofthrones.data.source.RemoteDataSource
import com.alvaronunez.gameofthrones.utils.MockUtils
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class FakeRemoteDataSource(private val moshi: Moshi, private val context: Context) : RemoteDataSource {
    override suspend fun getCategories(): Result<List<CategoryDTO>> =
        moshi.adapter<List<CategoryDTO>>(Types.newParameterizedType(List::class.java, CategoryDTO::class.java)).fromJson(
            MockUtils.readJsonFile(context, "categories.json"))?.let { dto ->
            Result.Response(dto)
        } ?: Result.Error()

    override suspend fun getBooks(): Result<List<BookDTO>> =
        moshi.adapter<List<BookDTO>>(Types.newParameterizedType(List::class.java, BookDTO::class.java)).fromJson(
            MockUtils.readJsonFile(context, "books.json"))?.let { dto ->
            Result.Response(dto)
        } ?: Result.Error()


    override suspend fun getHouses(): Result<List<HouseDTO>> =
        moshi.adapter<List<HouseDTO>>(Types.newParameterizedType(List::class.java, HouseDTO::class.java)).fromJson(
            MockUtils.readJsonFile(context, "houses.json"))?.let { dto ->
            Result.Response(dto)
        } ?: Result.Error()

    override suspend fun getCharacters(): Result<List<CharDTO>> =
        moshi.adapter<List<CharDTO>>(Types.newParameterizedType(List::class.java, CharDTO::class.java)).fromJson(
            MockUtils.readJsonFile(context, "characters.json"))?.let { dto ->
            Result.Response(dto)
        } ?: Result.Error()

}