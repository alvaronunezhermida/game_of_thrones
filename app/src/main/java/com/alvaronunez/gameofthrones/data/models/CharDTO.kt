package com.alvaronunez.gameofthrones.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharDTO (
    @Json(name="id")
    val id: String? = null,
    @Json(name="name")
    val name: String? = null,
    @Json(name="gender")
    val gender: String? = null,
    @Json(name="culture")
    val culture: String? = null,
    @Json(name="born")
    val born: String? = null,
    @Json(name="died")
    val died: String? = null,
    @Json(name="titles")
    val titles: List<String>? = null,
    @Json(name="aliases")
    val aliases: List<String>? = null,
    @Json(name="father")
    val father: String? = null,
    @Json(name="mother")
    val mother: String? = null,
    @Json(name="spouse")
    val spouse: String? = null,
    @Json(name="allegiances")
    val allegiances: List<String>? = null,
    @Json(name="playedBy")
    val playedBy: List<String>? = null
)