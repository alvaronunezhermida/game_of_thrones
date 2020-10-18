package com.alvaronunez.gameofthrones.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HouseDTO (
    @Json(name="id")
    val id: String? = null,
    @Json(name="name")
    val name: String? = null,
    @Json(name="region")
    val region: String? = null,
    @Json(name="title")
    val title: String? = null
)