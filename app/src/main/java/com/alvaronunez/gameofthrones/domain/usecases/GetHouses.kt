package com.alvaronunez.gameofthrones.domain.usecases

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.HouseDTO
import com.alvaronunez.gameofthrones.data.repository.Repository

class GetHouses(private val repository: Repository) {
    suspend fun invoke(onResult: (Result<List<HouseDTO>>) -> Unit) {
        onResult(repository.getHouses())
    }
}