package com.alvaronunez.gameofthrones.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookDTO (
    @Json(name="name")
    val name: String? = null,
    @Json(name="isbn")
    val isbn: String? = null,
    @Json(name="authors")
    val authors: List<String>? = null,
    @Json(name="numberOfPages")
    val numberOfPages: Int? = null,
    @Json(name="publisher")
    val publisher: String? = null,
    @Json(name="country")
    val country: String? = null,
    @Json(name="mediaType")
    val mediaType: String? = null,
    @Json(name="released")
    val released: String? = null
)