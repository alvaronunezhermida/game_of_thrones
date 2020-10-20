package com.alvaronunez.gameofthrones.presentation.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alvaronunez.gameofthrones.presentation.data.database.daos.CategoryDao
import com.alvaronunez.gameofthrones.presentation.data.database.entities.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movie-db"
        ).build()
    }

    abstract fun categoryDao(): CategoryDao
}