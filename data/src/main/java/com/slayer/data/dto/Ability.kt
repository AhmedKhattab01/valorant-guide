package com.slayer.data.dto


import com.squareup.moshi.Json

data class Ability(
    @Json(name = "description")
    val description: String?,
    @Json(name = "displayIcon")
    val displayIcon: String?,
    @Json(name = "displayName")
    val displayName: String?,
    @Json(name = "slot")
    val slot: String?
)