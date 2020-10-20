package com.alvaronunez.gameofthrones.presentation.data.service


import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.data.models.CharDTO
import com.alvaronunez.gameofthrones.data.models.HouseDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService{
    @GET("categories")
    fun getCategoriesAsync(): Deferred<Result<List<CategoryDTO>>>

    @GET("list/1")
    fun getBooksAsync(): Deferred<Result<List<BookDTO>>>

    @GET("list/2")
    fun getHousesAsync(): Deferred<Result<List<HouseDTO>>>

    @GET("list/3")
    fun getCharactersAsync(): Deferred<Result<List<CharDTO>>>
}