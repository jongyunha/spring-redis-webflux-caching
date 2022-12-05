package com.jongyun.redisspring.city.dto

data class City(
    val zip: String,
    val city: String,
    val stateName: String,
    val temperature: Int,
)