package com.alvaronunez.gameofthrones.presentation.data.service

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.data.models.CharDTO
import com.alvaronunez.gameofthrones.data.models.HouseDTO
import com.alvaronunez.gameofthrones.data.source.RemoteDataSource


class ServiceDataSource(private val service: Service): RemoteDataSource {

    override suspend fun getCategories(): Result<List<CategoryDTO>> =
        try {
            Result.Response(service.apiService
                .getCategoriesAsync().await())
        }catch (e: Exception) {
            Result.Error(e.message)
        }


    override suspend fun getBooks(): Result<List<BookDTO>> =
        try {
            Result.Response(service.apiService
                    .getBooksAsync().await())
        }catch (e: Exception) {
            Result.Error(e.message)
        }

    override suspend fun getHouses(): Result<List<HouseDTO>> =
        try {
            Result.Response(service.apiService
                        .getHousesAsync().await())
        }catch (e: Exception) {
            Result.Error(e.message)
        }

    override suspend fun getCharacters(): Result<List<CharDTO>> =
        try {
            Result.Response(service.apiService
                        .getCharactersAsync().await())
        }catch (e: Exception) {
            Result.Error(e.message)
        }

}