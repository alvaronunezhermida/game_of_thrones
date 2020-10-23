package com.alvaronunez.gameofthrones.domain.usecases

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CharDTO
import com.alvaronunez.gameofthrones.data.repository.Repository

class GetCharacters(private val repository: Repository) {
    suspend fun invoke(onResult: (Result<List<CharDTO>>) -> Unit) {
        onResult(repository.getCharacters())
    }
}