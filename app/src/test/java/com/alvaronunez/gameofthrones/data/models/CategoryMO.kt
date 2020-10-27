package com.alvaronunez.gameofthrones.data.models

object CategoryMO {
    fun normalCategory() = CategoryDTO(
            name = "Mocked Name",
            type = 0
    )

    fun categoryType1() = CategoryDTO(
            name = "Mocked Name 1",
            type = 1
    )
}