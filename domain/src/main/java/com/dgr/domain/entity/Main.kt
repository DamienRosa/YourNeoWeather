package com.dgr.domain.entity

data class Main(
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Double,
    val temp: Double,
    val temp_kf: Int,
    val temp_max: Double,
    val temp_min: Double
)