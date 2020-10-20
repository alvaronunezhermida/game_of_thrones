package com.alvaronunez.gameofthrones.presentation.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val type: Int
)