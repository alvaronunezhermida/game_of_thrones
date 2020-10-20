package com.alvaronunez.gameofthrones.presentation.data

import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.presentation.data.database.entities.CategoryEntity

fun CategoryDTO.toRoomCategoryEntity(): CategoryEntity =
    CategoryEntity(
        null,
        name,
        type
    )

fun CategoryEntity.toCategoryDTO(): CategoryDTO =
    CategoryDTO(
        name,
        type
    )