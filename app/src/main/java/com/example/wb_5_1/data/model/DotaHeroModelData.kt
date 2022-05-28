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
    val icon: String,

    @Json(name = "img")
    val img: String,

    @Json(name = "base_health")
    val baseHealth: Double,

    @Json(name = "base_health_regen")
    val baseHealthRegen: Double,

    @Json(name = "base_mana")
    val baseMana: Double,

    @Json(name = "base_mana_regen")
    val baseManaRegen: Double,

    @Json(name = "base_armor")
    val baseArmor: Double,

    @Json(name = "base_mr")
    val baseMr: Double,

    @Json(name = "base_attack_min")
    val baseAttackMin: Double,

    @Json(name = "base_attack_max")
    val baseAttackMax: Double,

    @Json(name = "base_str")
    val baseStr: Double,

    @Json(name = "base_agi")
    val baseAgi: Double,

    @Json(name = "base_int")
    val baseInt: Double,

    @Json(name = "str_gain")
    val strGain: Double,

    @Json(name = "agi_gain")
    val agiGain: Double,

    @Json(name = "int_gain")
    val intGain: Double,

    @Json(name = "attack_range")
    val attackRange: Double,

    @Json(name = "move_speed")
    val moveSpeed: Double,

    @Json(name = "legs")
    val legs: Int

)
