package com.example.wb_5_1.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DotaHeroModelData(
    @Json(name = "id")
    val id: Int,
    @Json(name = "localized_name")
    val name: String,
    @Json(name = "primary_attr")
    val primaryAttr: String,
    @Json(name = "attack_type")
    val attackType: String,
    @Json(name = "icon")
    val icon: String
)
