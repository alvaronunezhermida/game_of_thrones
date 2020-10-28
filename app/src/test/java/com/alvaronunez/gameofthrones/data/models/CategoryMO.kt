package com.alvaronunez.gameofthrones.data.models

object CategoryMO {
    fun normalCategory() = CategoryDTO(
            name = "Mocked Name",
            type = 0
    )

    fun categoryType0() = CategoryDTO(
            name = "Mocked Name 0",
            type = 0
    )

    fun categoryType1() = CategoryDTO(
            name = "Mocked Name 1",
            type = 1
    )

    fun categoryType2() = CategoryDTO(
            name = "Mocked Name 2",
            type = 2
    )
}