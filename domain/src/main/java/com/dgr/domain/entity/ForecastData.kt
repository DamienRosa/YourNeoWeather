package com.dgr.domain.entity

data class ForecastData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val message: Double
)