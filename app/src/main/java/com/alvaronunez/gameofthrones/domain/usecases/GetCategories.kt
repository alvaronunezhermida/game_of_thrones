package com.alvaronunez.gameofthrones.domain.usecases

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.data.repository.Repository

class GetCategories(private val repository: Repository) {
    suspend fun invoke(onResult: (Result<List<CategoryDTO>>) -> Unit ) {
        onResult(repository.getCategories())
    }
}