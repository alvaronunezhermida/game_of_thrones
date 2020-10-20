package com.alvaronunez.gameofthrones.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryDTO (
    @Json(name="category_name")
    val name: String,
    @Json(name="type")
    val type: Int
)