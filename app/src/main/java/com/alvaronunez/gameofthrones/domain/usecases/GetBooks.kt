package com.alvaronunez.gameofthrones.domain.usecases

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.data.repository.Repository

class GetBooks(private val repository: Repository) {
    suspend fun invoke(onResult: (Result<List<BookDTO>>) -> Unit) {
        onResult(repository.getBooks())
    }
}