package com.alvaronunez.gameofthrones.data.source

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.data.models.CharDTO
import com.alvaronunez.gameofthrones.data.models.HouseDTO

interface RemoteDataSource {
    suspend fun getCategories(): Result<List<CategoryDTO>>
    suspend fun getBooks(): Result<List<BookDTO>>
    suspend fun getHouses(): Result<List<HouseDTO>>
    suspend fun getCharacters(): Result<List<CharDTO>>
}