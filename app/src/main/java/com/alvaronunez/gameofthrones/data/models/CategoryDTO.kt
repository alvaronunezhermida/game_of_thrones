package com.alvaronunez.gameofthrones.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// TODO: 20/10/2020 Check if annotations are correct for moshi to retrofit conversion
@JsonClass(generateAdapter = true)
data class CategoryDTO (
    val id: Int,
    @Json(name="category_name")
    val name: String,
    @Json(name="type")
    val type: Int
)