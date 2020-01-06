package com.dgr.domain.entity

data class CityWeather(
    val country: String,
    val name: String,
    val temperature: Double,
    val humidity: String,
    val weatherIcon: String?,
    val lastUpdateDate: String
)