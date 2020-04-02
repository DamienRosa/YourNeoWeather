package com.dgr.domain.entity

data class ForecastDomain(
    val city: String,
    val cnt: Int,
    val cod: String,
    val message: Double
)
