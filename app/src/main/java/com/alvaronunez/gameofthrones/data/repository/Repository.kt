package com.alvaronunez.gameofthrones.data.repository

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.data.source.LocalDataSource
import com.alvaronunez.gameofthrones.data.source.RemoteDataSource

class Repository(
        private val localDataSource: LocalDataSource,
        private val remoteDataSource: RemoteDataSource
) {

    suspend fun getCategories(): Result<List<CategoryDTO>> {
        return if (localDataSource.isCategoriesEmpty()) {
            remoteDataSource.getCategories().also { remoteResult ->
                if (remoteResult is Result.Response && remoteResult.data.isNotEmpty()) localDataSource.saveCategories(remoteResult.data)
            }
        } else {
            localDataSource.getCategories()
        }
    }

    suspend fun getBooks() = remoteDataSource.getBooks()

    suspend fun getHouses() = remoteDataSource.getHouses()

    suspend fun getCharacters() = remoteDataSource.getCharacters()


}