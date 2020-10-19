package com.alvaronunez.gameofthrones.presentation.data.service

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.data.models.CharDTO
import com.alvaronunez.gameofthrones.data.models.HouseDTO
import com.alvaronunez.gameofthrones.data.source.RemoteDataSource


class ServiceDataSource(private val service: Service): RemoteDataSource {

    override suspend fun getCategories(): Result<List<CategoryDTO>> =
        service.apiService
            .getCategories().await()


    override suspend fun getBooks(): Result<List<BookDTO>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHouses(): Result<List<HouseDTO>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacters(): Result<List<CharDTO>> {
        TODO("Not yet implemented")
    }

}